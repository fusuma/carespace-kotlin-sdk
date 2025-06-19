plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.dokka")
    `maven-publish`
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    
    android {
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                implementation("io.ktor:ktor-client-core:2.3.4")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
                implementation("io.ktor:ktor-client-logging:2.3.4")
                implementation("io.ktor:ktor-client-auth:2.3.4")
                implementation("io.insert-koin:koin-core:3.5.0")
            }
        }
        
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
                implementation("io.ktor:ktor-client-mock:2.3.4")
                implementation("io.mockk:mockk:1.13.8")
            }
        }
        
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.3.4")
                implementation("ch.qos.logback:logback-classic:1.4.11")
            }
        }
        
        val jvmTest by getting {
            dependencies {
                implementation("io.mockk:mockk:1.13.8")
            }
        }
        
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.3.4")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
                implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
                implementation("io.insert-koin:koin-android:3.5.0")
            }
        }
    }
}

android {
    namespace = "com.carespace.sdk"
    compileSdk = 34
    
    defaultConfig {
        minSdk = 24
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.carespace"
            artifactId = "sdk-kotlin"
            version = "1.0.0"
            
            pom {
                name.set("Carespace Kotlin SDK")
                description.set("Official Kotlin SDK for the Carespace API")
                url.set("https://github.com/carespace/sdk-monorepo")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("carespace")
                        name.set("Carespace Team")
                        email.set("support@carespace.ai")
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/carespace/sdk-monorepo.git")
                    developerConnection.set("scm:git:ssh://github.com/carespace/sdk-monorepo.git")
                    url.set("https://github.com/carespace/sdk-monorepo")
                }
            }
        }
    }
}