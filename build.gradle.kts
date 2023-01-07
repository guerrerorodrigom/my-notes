import Dependencies.isNonStable
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(Dependencies.Gradle.androidAppGradle) version Dependencies.Versions.gradleAndroid apply false
    id(Dependencies.Gradle.androidLibraryGradle) version Dependencies.Versions.gradleAndroid apply false
    id(Dependencies.Gradle.kotlinGradle) version Dependencies.Versions.gradleKotlinPlugin apply false
    id(Dependencies.Gradle.versionCheck) version Dependencies.Versions.versionCheck
    id(Dependencies.Gradle.hilt) version Android.Versions.hilt apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"

    rejectVersionIf {
        isNonStable(this.candidate.version)
    }
}
