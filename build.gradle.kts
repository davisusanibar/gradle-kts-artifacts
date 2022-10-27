plugins {
    java
    `maven-publish`
    id("net.researchgate.release") version "3.0.2"
}

group = "org.ddsa"
version = "${version}"

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

allprojects {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
        System.out.println("-------")
        System.out.println(version)
        repositories {
            maven {
                name = "GITHUB"
                val releasesRepoUrl = System.getenv("GITHUB_URL").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_URL"].toString()
                url = uri(releasesRepoUrl)
                credentials {
                    username = System.getenv("GITHUB_ACTOR").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_ACTOR"].toString()
                    password = System.getenv("GITHUB_TOKEN").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_TOKEN"].toString()
                }
            }
            maven {
                name = "NEXUS"
                val nexusUrl = System.getenv("NEXUS_URL").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_URL"].toString()
                val releasesRepoUrl = nexusUrl + "/maven-releases"
                val snapshotsRepoUrl = nexusUrl + "/maven-snapshots"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                isAllowInsecureProtocol = System.getenv().containsKey("NEXUS_INSECURE").takeUnless { it == false } ?: extra["NEXUS_INSECURE"].toString().toBoolean()
                credentials {
                    username = System.getenv("NEXUS_USERNAME").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_USERNAME"].toString()
                    password = System.getenv("NEXUS_PASSWORD").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_PASSWORD"].toString()
                }
            }
        }
    }
}

release {
}
