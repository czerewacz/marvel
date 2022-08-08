plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.JETBRAINS_KOTLIN)
    id(BuildPlugins.NAV_SAFE_ARGS)
    id(BuildPlugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK
        targetSdk = BuildAndroidConfig.TARGET_SDK
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //Modules
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Features.CHARACTERS))

    //Android lifecycle
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.livedata)
    implementation(Dependencies.AndroidX.viewmodel)

    //Android Navigation
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigationUI)

    //Ktx
    implementation(Dependencies.AndroidX.ktx)

    //Android
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.AndroidX.constraintLayout)

    //Dependency Injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)

    //Paging
    implementation(Dependencies.AndroidX.paging)

    //Coil
    implementation(Dependencies.Coil.coilKt)

}