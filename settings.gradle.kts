rootProject.name = "gradle-01"

//plugins {
//    id("maven-publish")
//}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

pluginManagement {
    plugins {
        fun String.v() = extra["$this.version"].toString()
        fun PluginDependenciesSpec.idv(id: String, key: String = id) = id(id) version key.v()
        kotlin("jvm") version "kotlin".v()
        `maven-publish`
    }
    if (
        extra.has("enableMavenLocal") &&
        extra["enableMavenLocal"].toString().ifBlank { "true" }.toBoolean()
    ) {
        System.out.println("Maven enabled")
        repositories {
            mavenLocal()
            gradlePluginPortal()
        }
    } else {
        System.out.println("Maven disabled")
    }

}