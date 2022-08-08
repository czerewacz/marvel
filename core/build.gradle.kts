import java.util.Properties
import java.io.FileInputStream

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.JETBRAINS_KOTLIN)
    id(BuildPlugins.KOTLIN_SERIALIZATION) version "1.6.20"
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK
        targetSdk = BuildAndroidConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")


        val properties = Properties().apply {load(
            FileInputStream(
                File(
                    rootProject.rootDir,
                    "local.properties"
                )
            )
        )}
        buildConfigField("String", "HASH_KEY", "\"${properties.getProperty("HASH_KEY")}\"")
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

    //Ktx
    implementation(Dependencies.AndroidX.ktx)

    //Android
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)

    //Dependency Injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)

    //Network Libs
    implementation(Dependencies.Ktor.ktorCio)
    implementation(Dependencies.Ktor.ktorLog)
    implementation(Dependencies.Logging.slf4j)
    implementation(Dependencies.Ktor.ktorJsonSerial)
    implementation(Dependencies.Ktor.contentNegotiation)

    //Testing
    testImplementation(TestDependencies.Junit.junitLib)
    testImplementation(TestDependencies.Mock.mockk)
    testImplementation(TestDependencies.Coroutines.coroutinesLib)
    testImplementation(TestDependencies.Junit.koin)
    testImplementation(TestDependencies.Koin.koinLib)
    testImplementation(TestDependencies.AndroidArch.androidArchLib)
    testImplementation(TestDependencies.Ktor.ktorLib)

}