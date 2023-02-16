/**
 * Project dependencies
 */

object Dependencies {
    object AndroidX {
        // Kotlin extensions for android
        const val ktx = "androidx.core:core-ktx:${DependenciesVersions.ktx}"

        // AppCompat library
        const val appcompat = "androidx.appcompat:appcompat:${DependenciesVersions.appcompat}"

        // AppCompat library
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${DependenciesVersions.constraint}"

        // Android lifecycle runtime
        const val lifecycle =
            "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.lifecycle}"

        //Android lifecycle livedata
        const val livedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${DependenciesVersions.lifecycle}"

        //Android lifecycle viewmodel
        const val viewmodel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.lifecycle}"

        //Navigation
        const val navigation =
            "androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.navigation}"

        //Navigation ui
        const val navigationUI =
            "androidx.navigation:navigation-ui-ktx:${DependenciesVersions.navigation}"

        //Paging 3
        const val paging = "androidx.paging:paging-runtime:${DependenciesVersions.paging}"
        const val pagingNonAndroid = "androidx.paging:paging-common:${DependenciesVersions.paging}"
    }

    object Google {
        // Android Material Design Components
        const val material = "com.google.android.material:material:${DependenciesVersions.material}"
    }

    object DI {
        // Koin for Kotlin Multiplatform
        const val koin = "io.insert-koin:koin-core:${DependenciesVersions.koin}"

        // Koin main features for Android (Scope,ViewModel ...)
        const val koinAndroid = "io.insert-koin:koin-android:${DependenciesVersions.koin}"
    }

    object Ktor {
        const val ktor = "io.ktor:ktor-client-core:${DependenciesVersions.ktor}"
        const val ktorCio = "io.ktor:ktor-client-cio:${DependenciesVersions.ktor}"
        const val ktorLog = "io.ktor:ktor-client-logging:${DependenciesVersions.ktor}"
        const val logNative = "io.ktor:ktor-client-logging-native:${DependenciesVersions.ktor}"
        const val ktorJsonSerial =
            "io.ktor:ktor-serialization-kotlinx-json:${DependenciesVersions.ktor}"
        const val ktorSerial = "io.ktor:ktor-client-serialization:${DependenciesVersions.ktor}"
        const val contentNegotiation =
            "io.ktor:ktor-client-content-negotiation:${DependenciesVersions.ktor}"
    }

    object Logging {
        const val slf4j = "org.slf4j:slf4j-simple:${DependenciesVersions.slf4j}"
    }

    object Coil {
        const val coilKt =
            "io.coil-kt:coil:${DependenciesVersions.coil}"
    }
}