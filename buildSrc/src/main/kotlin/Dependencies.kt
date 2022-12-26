object Dependencies {
    object Versions {
        const val androidJUnit = "1.1.4"
        const val espresso = "3.5.0"
        const val gradleAndroid = "7.3.1"
        const val gradleKotlinPlugin = "1.7.20"
        const val jUnit = "4.13.2"
        const val versionCheck = "0.44.0"
    }

    object Gradle {
        const val androidAppGradle = "com.android.application"
        const val androidLibraryGradle = "com.android.library"
        const val versionCheck = "com.github.ben-manes.versions"
        const val kotlinGradle = "org.jetbrains.kotlin.android"
    }

    object Testing {
        const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Android.Versions.compose}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val jUnit = "junit:junit:${Versions.jUnit}"
    }

    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}