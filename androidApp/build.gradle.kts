plugins {
    id("com.android.application")
    id("com.google.devtools.ksp") version "1.5.31-1.0.0"
    kotlin("android")
}

android {
    compileSdk = AndroidConfigs.compileSdkVersion

    defaultConfig {
        applicationId = AndroidConfigs.applicationId
        minSdk = AndroidConfigs.minSdkVersion
        targetSdk = AndroidConfigs.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"

    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfigs.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation("io.github.raamcosta.compose-destinations:core:1.1.5-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.1.5-beta")

    with(Dependencies) {
        implementation(androidMaterial)
        implementation(androidAppCompat)
        implementation(constraintLayout)
        implementation(fragmentNavigation)
        implementation(androidNavigation)
        implementation(liveDataKtx)

        implementation(composeUi)
        implementation(composeMaterial)
        implementation(composeTooling)
        implementation(composeFoundation)
        implementation(composeFoundationLayout)
        implementation(composeGraphics)
        implementation(composeActivity)
        implementation(composeNavigation)
        implementation(composeMaterialIcon)
        implementation(composeUtil)
        implementation(composeLottie)
        implementation(composeShimmer)
        implementation(composeLiveData)

        implementation(ktorAndroid)
        implementation(koinAndroid)
        implementation(koinCompose)

        implementation(accompanistCoil)
        implementation(accompanistPager)

        implementation(voyagerNavigator)
        implementation(voyagerTabNavigator)
        implementation(voyagerTransitions)
        implementation(voyagerAndroidx)
        implementation(voyagerLivedata)
        implementation(voyagerKoin)
    }
}