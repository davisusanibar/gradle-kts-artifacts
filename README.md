# gradle-kts-artifacts

## Maven Use
pom.xml
````
    <repositories>
        <repository>
            <id>github</id>
            <url>https://maven.pkg.github.com/davisusanibar/gradle-kts-artifacts</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.ddsa</groupId>
            <artifactId>gradle-01</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
````

settings.xml
````
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>github</id>
      <username>davisusanibar</username>
      <password>YOUR_TOKEN_WITH_READ_ACCESS</password>
    </server>
  </servers>
</settings>
````

## Gradle Use
build.gradle
````
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/davisusanibar/gradle-kts-artifacts")
        credentials {
            username = System.getenv("USERNAME")
            password = System.getenv("GITHUB_TOKEN_READ")
        }
    }
}

dependencies {
    implementation 'org.ddsa:gradle-01:1.0.0-SNAPSHOT'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}
````
