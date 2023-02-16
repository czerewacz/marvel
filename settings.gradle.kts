pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Marvel"
include(
    ":app",
    ":core",
    ":features:character:character_domain",
    ":features:character:character_data",
    ":features:character:character_presentation"
)

