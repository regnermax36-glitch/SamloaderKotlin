@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        
        // Alternative Maven Central mirrors
        maven("https://repo1.maven.org/maven2/")
        maven("https://central.maven.org/maven2/")
        maven("https://oss.sonatype.org/content/repositories/releases/")

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
        maven("https://maven.hq.hydraulic.software")
        maven("file:libs/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Primary repositories
        google()
        mavenCentral()
        
        // Alternative Maven Central mirrors for CI environments
        maven("https://repo1.maven.org/maven2/")
        maven("https://central.maven.org/maven2/")
        maven("https://oss.sonatype.org/content/repositories/releases/")
        maven("https://s01.oss.sonatype.org/content/repositories/releases/")
        
        // JetBrains repositories
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap/")
        
        // Additional repositories
        maven("https://jitpack.io") {
            content {
                includeGroupByRegex("com\\.github\\..*")
                includeGroupByRegex("io\\.github\\..*")
            }
        }
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://repo.jenkins-ci.org/public/")
        maven("file:libs/")
        
        // Gradle Plugin Portal as fallback
        gradlePluginPortal()
    }
}

rootProject.name = "SamloaderKotlin"
include(":android")
include(":desktop")
include(":common")
