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

GPG
```shell
$ gpg --full-generate-key
$ gpg --list-keys --keyid-format LONG
/Users/dsusanibar/.gnupg/pubring.kbx
------------------------------------
pub   ed25519/XXXXXXXXXXXXXXX 2022-11-02 [SC] [expires: 2024-11-01]
      YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
uid                 [ultimate] David Dali Susanibar Arce <dsusanibara@uni.pe>
sub   cv25519/ZZZZZZZZZZZZZZZZ 2022-11-02 [E] [expires: 2024-11-01]

$ gpg --keyring secring.gpg --export-secret-key XXXXXXXX > ~/.gnupg/secring.gpg
$ gpg --keyring secring.gpg --export-secret-key C9D32C35 > ~/.gnupg/secring.gpg


$ gpg --armor --export 0804BDA3C9D32C35
```

```shell
signing.keyId=1A2345F8
signing.password=
signing.secretKeyRingFile=/Users/jwilson/.gnupg/secring.gpg
```

```shell
Export Public Key
This command will export an ascii armored version of the public key:
gpg --output public.pgp --armor --export username@email

Export Secret Key
This command will export an ascii armored version of the secret key:
gpg --output private.pgp --armor --export-secret-key username@email
```


gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg

gpg --keyring secringv3.gpg --export-secret-keys > ~/.gnupg/secringv3.gpg
