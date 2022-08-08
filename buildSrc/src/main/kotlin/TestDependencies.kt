object TestDependencies {

    object Junit {
        const val junitLib = "junit:junit:${TestDependenciesVersions.junit}"
        const val koin = "io.insert-koin:koin-test-junit4:${DependenciesVersions.koin}"
    }

    object Mock {
        const val mockk = "io.mockk:mockk:${TestDependenciesVersions.mockk}"
    }

    object Coroutines {
        const val coroutinesLib =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestDependenciesVersions.coroutines}"
    }

    object Koin {
        const val koinLib = "io.insert-koin:koin-test:${DependenciesVersions.koin}"
    }

    object AndroidArch {
        const val androidArchLib =
            "androidx.arch.core:core-testing:${TestDependenciesVersions.androidArch}"
    }

    object Ktor {
        const val ktorLib = "io.ktor:ktor-client-mock:${DependenciesVersions.ktor}"
    }
}