plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.JETBRAINS_KOTLIN)
}

android {
    namespace = BuildAndroidConfig.CHARACTER_DOMAIN_NAMESPACE
    compileSdk = BuildAndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK
        targetSdk = BuildAndroidConfig.TARGET_SDK

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
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    //Modules
    implementation(project(BuildModules.core))

    //Dependency Injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)

    //Paging3
    implementation(Dependencies.AndroidX.pagingNonAndroid)

    //Testing
    testImplementation(TestDependencies.Junit.junitLib)
    testImplementation(TestDependencies.Mock.mockk)
    testImplementation(TestDependencies.Coroutines.coroutinesLib)
    testImplementation(TestDependencies.Junit.koin)
    testImplementation(TestDependencies.Koin.koinLib)
    testImplementation(TestDependencies.AndroidArch.androidArchLib)
    testImplementation(TestDependencies.Ktor.ktorLib)
}