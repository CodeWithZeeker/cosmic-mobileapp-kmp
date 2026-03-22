plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.skie)
}

kotlin {
    androidLibrary {
        namespace = "com.jewel.cosmicapp.shared.app"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Shared"
            isStatic = true
            export(projects.shared.features.login)
            export(projects.shared.foundation.network)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.shared.foundation.network)
            api(projects.shared.foundation.database)
            api(projects.shared.features.login)
            api(projects.shared.features.dashboard)
            api(projects.shared.features.compatibility)
            implementation(libs.koin.core)
        }
        
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}
