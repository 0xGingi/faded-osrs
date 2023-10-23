@file:Suppress("UnstableApiUsage")

rootProject.name = "faded-server"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
    }
    pluginManagement.plugins.apply {
        kotlin("jvm").version("1.8.22")
    }
}
