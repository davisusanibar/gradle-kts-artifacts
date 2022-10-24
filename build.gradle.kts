plugins {
    java
    `maven-publish`
}

group = "org.ddsa"
version = "1.0.0-SNAPSHOT"


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
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/davisusanibar/gradle-kts-artifacts")
                credentials {
                    username = System.getenv("TESTUSER")
                    password = System.getenv("TESTPASS")
                }
            }
        }
    }
}
