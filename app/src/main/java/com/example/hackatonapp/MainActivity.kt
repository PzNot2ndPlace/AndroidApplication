package com.example.hackatonapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.hackatonapp.presentation.LocationService
import com.example.hackatonapp.presentation.navigation.WelcomeNavHost
import com.example.hackatonapp.ui.theme.HackatonAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestPostNotificationsPermission()
        requestLocationPermission()
        requestServicePermission()

        val insetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        if (insetsController != null) {
            insetsController.hide(WindowInsetsCompat.Type.navigationBars())
            insetsController.hide(WindowInsetsCompat.Type.statusBars())
            insetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        val serviceIntent = Intent(this, LocationService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)

        setContent {
            HackatonAppTheme {
                WelcomeNavHost()
            }
        }
    }

    private fun requestPostNotificationsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        }
    }

    private fun requestServicePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.FOREGROUND_SERVICE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.FOREGROUND_SERVICE), 0)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.FOREGROUND_SERVICE
            ) == PackageManager.PERMISSION_GRANTED
        ){

        }
    }
}
