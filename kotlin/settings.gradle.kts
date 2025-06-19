rootProject.name = "carespace-kotlin-sdk"

include(":carespace-sdk")
include(":examples:android-app")
include(":examples:jvm-app")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}