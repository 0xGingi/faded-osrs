rootProject.name = "faded-client"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.runelite.net")
        jcenter()
    }
}

pluginManagement {
    plugins {
        id("com.github.johnrengelman.shadow") version "8.1.1"
    }
}
