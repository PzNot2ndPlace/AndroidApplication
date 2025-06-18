package com.example.hackatonapp.presentation

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.hackatonapp.R
import com.example.hackatonapp.network.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    @Inject lateinit var repository: LocationRepository

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_ID, createNotification())

        coroutineScope.launch {
            while (isActive) {
                sendLocation()
                delay(10_000)
            }
        }

        return START_STICKY
    }

    private fun sendLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val lat = it.latitude
                val lon = it.longitude


                coroutineScope.launch {
                    repository.sendUserLocation("$lat $lon").collect()
                }
            }
        }
    }

    private fun createNotification(): Notification {
        val channelId = "location_channel"
        val channelName = "Location Tracking"
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(chan)

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Отслеживание геопозиции")
            .setContentText("Сервис активен")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        private const val NOTIFICATION_ID = 1
    }
}