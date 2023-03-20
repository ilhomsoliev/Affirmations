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
      //  maven("https://androidx.dev/snapshots/latest/artifacts/repository")
    }
}
rootProject.name = "Affirmations"
include(":app")