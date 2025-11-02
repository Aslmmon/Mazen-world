package com.aslmmovic.mazenworld

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.aslmmovic.mazenworld.di.initKoin
import com.google.firebase.Firebase
import org.koin.android.ext.koin.androidContext
import java.util.Locale

class MainActivity : ComponentActivity() {

    val isArabic = false

    override fun attachBaseContext(newBase: Context) {
        if (isArabic) {
            val locale = Locale("ar") // Target Arabic
            val config = Configuration(newBase.resources.configuration)
            config.setLocale(locale)
            val context = newBase.createConfigurationContext(config)
            super.attachBaseContext(ContextWrapper(context))

        } else {
            super.attachBaseContext(newBase)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initKoin {
            // This assumes your Koin module has Android-specific dependencies
            // that require context, e.g., for DataStore/SharedPreferences setup.
            androidContext(this@MainActivity)
        }
        hideSystemUI()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }

    }

    // New function to handle the immersive UI logic
    private fun hideSystemUI() {
        // Tell the window not to fit its content to the system windows (i.e., hide system bars)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Get the system controller
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            // Hide both the System Bars (Status Bar and Navigation Bar)
            controller.hide(WindowInsetsCompat.Type.systemBars())

            // Set the behavior to show bars temporarily when the user swipes,
            // but hide them again quickly (full immersive experience)
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}