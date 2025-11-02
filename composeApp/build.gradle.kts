import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.kotlinSerialization)


}


kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

//    listOf(
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }



    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.androidx.ui.text.android)

        }
        commonMain.dependencies {
            /**
             * Android Dependencies
             */
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.ui.text.android)

            /**
             * Koin DI
             */
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            /**
             * Navigation
             */
            implementation(libs.navigation.compose)
            /**
             * Firebase
             */
            implementation(libs.firebase.firestore) // <-- This should point to dev.gitlive:firebase-firestore


            /**
             * Coil for Image
             */
            implementation("io.coil-kt.coil3:coil-compose:3.3.0")
            implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")
            implementation("io.coil-kt.coil3:coil-svg:3.3.0")

            /**
             * lottie
             */
            implementation(libs.lottie.compose)
//            implementation(libs.compottie.dot)
//            implementation("io.github.alexzhirkevich:compottie-network:2.0.2")


        }

    }
}

android {
    namespace = "com.aslmmovic.mazenworld"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.aslmmovic.mazenworld"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
