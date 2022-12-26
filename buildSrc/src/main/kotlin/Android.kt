object Android {
    object Versions {
        const val activityCompose = "1.6.1"
        const val compose = "1.3.2"
        const val coreKtx = "1.9.0"
        const val lifecycleRuntimeKtx = "2.5.1"
        const val material = "1.3.1"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val material = "androidx.compose.material:material:${Versions.material}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

    object Jetpack {

    }
}