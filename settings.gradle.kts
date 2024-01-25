pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenCentral(){
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "openning assignment"
include(":app")
include(":network")
