import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.5.0"
    id("com.android.library")
    id("io.realm.kotlin") version Versions.realm
    id("com.rickclephas.kmp.nativecoroutines") version "0.5.0"
    id("koin")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

android {
    compileSdk = AndroidConfigs.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = AndroidConfigs.minSdkVersion
        targetSdk = AndroidConfigs.targetSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}
    android()

    cocoapods {
        summary = "MangaApp"
        homepage = "https://github.com/uwaisalqadri/MangaKu"
        ios.deploymentTarget = "14.1"
        frameworkName = "KotlinCore"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Dependencies) {
                    implementation(realmKotlin)
                    implementation(koinCore)

                    implementation(ktorCore)
                    implementation(ktorClientSerialization)
                    implementation(ktorLogging)

                    implementation(ktxSerialization)
                    implementation(ktxCoroutine)
                    implementation(ktxDateTime)

                    api(kermitLogger)
                    implementation(kotlin("stdlib-common"))
                }
            }
        }
        val androidMain by getting {
            dependencies {
                with(Dependencies) {
                    implementation(ktorAndroid)
                    implementation(koinAndroid)
                }
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Dependencies.ktorIos)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosTest by getting
    }
}