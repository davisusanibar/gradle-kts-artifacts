plugins {
    java
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "io.github.davisusanibar"
version = "${version}"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

//allprojects {
//    publishing {
//        publications {
//            create<MavenPublication>("maven") {
//                from(components["java"])
//            }
//        }
//        repositories {
//            maven {
//                name = "GITHUB"
//                val releasesRepoUrl = System.getenv("GITHUB_URL").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_URL"].toString()
//                url = uri(releasesRepoUrl)
//                credentials {
//                    username = System.getenv("GITHUB_ACTOR").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_ACTOR"].toString()
//                    password = System.getenv("GITHUB_TOKEN").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_TOKEN"].toString()
//                }
//            }
//            maven {
//                name = "NEXUS"
//                val nexusUrl = System.getenv("NEXUS_URL").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_URL"].toString()
//                val releasesRepoUrl = nexusUrl + "/maven-releases"
//                val snapshotsRepoUrl = nexusUrl + "/maven-snapshots"
//                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
//                isAllowInsecureProtocol = System.getenv().containsKey("NEXUS_INSECURE").takeUnless { it == false } ?: extra["NEXUS_INSECURE"].toString().toBoolean()
//                credentials {
//                    username = System.getenv("NEXUS_USERNAME").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_USERNAME"].toString()
//                    password = System.getenv("NEXUS_PASSWORD").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_PASSWORD"].toString()
//                }
//            }
//        }
//    }
//}

allprojects {
    publishing {
        publications {
            create<MavenPublication>("mavensoyyo") {
                from(components["java"])

                pom {
                    name.set("Java Semantic Release")
                    description.set("Implement semantic release for Java projects")
                    url.set("https://github.com/davisusanibar/gradle-kts-artifacts")
                    properties.set(mapOf(
                        "country" to "PE",
                        "dsusanibar.type.of" to "Java"
                    ))
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("davisusanibar")
                            name.set("david dali susanibar arce")
                            email.set("dsusanibara@uni.pe")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com:davisusanibar/gradle-kts-artifacts.git")
                        developerConnection.set("scm:git:ssh://github.com:davisusanibar/gradle-kts-artifacts.git")
                        url.set("https://github.com/davisusanibar/gradle-kts-artifacts/")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "local"
                val releasesRepoUrl = "$buildDir/repos/releases"
                val snapshotsRepoUrl = "$buildDir/repos/snapshots"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            }
//            maven {
//                name = "nexusDirecto"
//                val nexusUrl = System.getenv("NEXUS_URL").takeUnless { it.isNullOrEmpty() } ?: extra["NEXUS_URL"].toString()
//                val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/";
//                val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/";
//                // url = uri(if (version.toString().contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
//                url = uri(snapshotsRepoUrl)
//                System.out.println("url is--->" + url)
//                credentials {
//                    username = "e8E1Gk71"
//                    password = "Fvs5U0NH0gn1R2UdINefJp4/H7xszovx2suOPZabpfFV"
//                }
//            }
            maven {
                name = "NexusRepository"
                url = uri("http://29bb-38-25-18-175.ngrok.io/repository/maven-releases")
                isAllowInsecureProtocol = true
                credentials {
                    username = "admin"
                    password = "admin"
                }
            }
        }
    }
//    nexusPublishing {
//        repositories {
//            create("myNexus") {
//                nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
//                snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
//                // nexusUrl.set(uri("http://localhost:8081/service/local/"))
//                    // snapshotRepositoryUrl.set(uri("http://localhost:8081/repository/maven-snapshots/"))
//                // allowInsecureProtocol.set(true)
//                username.set("e8E1Gk71")
//                password.set("Fvs5U0NH0gn1R2UdINefJp4/H7xszovx2suOPZabpfFV")
//            }
//        }
//    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
        withJavadocJar()
        withSourcesJar()
    }

    signing {
        sign(publishing.publications["mavensoyyo"])
    }

//    signing {
//        useGpgCmd()
////        sign(publishing.publications)
//        sign(publishing.publications["mavensoyyo"])
////        sign(configurations.archives.get())
////        sign(publishing.publications["mavensoyyo"])
////        sign(publications)
//    }
}
