plugins {
    kotlin("multiplatform") version "1.9.20" apply false
    kotlin("android") version "1.9.20" apply false
    kotlin("plugin.serialization") version "1.9.20" apply false
    id("com.android.application") version "8.1.2" apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.dokka") version "1.9.10" apply false
}

allprojects {
    group = "com.carespace"
    version = "1.0.0"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}