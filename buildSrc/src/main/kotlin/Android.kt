object Android {
    object Versions {
        const val accompanist = "0.28.0"
        const val activityCompose = "1.6.1"
        const val compose = "1.3.2"
        const val composeFoundation = "1.3.1"
        const val composeNavigation = "2.5.3"
        const val coreKtx = "1.9.0"
        const val hilt = "2.44"
        const val hiltNavigationCompose = "1.0.0"
        const val icons = "1.3.1"
        const val lifecycleRuntimeKtx = "2.5.1"
        const val material3 = "1.0.1"
        const val material = "1.3.1"
        const val room = "2.4.3"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"

    object Accompanist {
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.composeFoundation}"
        const val icons = "androidx.compose.material:material-icons-extended:${Versions.icons}"
        const val material3 = "androidx.compose.material3:material3:${Versions.material3}"
        const val material = "androidx.compose.material:material:${Versions.material}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    }

    object Jetpack {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }
}
