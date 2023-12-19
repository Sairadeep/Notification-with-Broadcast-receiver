package com.anxer.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MyNotificationService : Service() {

    private val filter = IntentFilter()
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onReceive(context: Context?, intent: Intent?) {
            notification(intent?.getStringExtra("name").toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        filter.addAction("Data_Changed")
        registerReceiver(receiver, filter, RECEIVER_NOT_EXPORTED)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notification(text: String) {
        val intent = Intent(this@MyNotificationService, MyNotificationService::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notificationChannelID = "Notification Service"
        val notificationBuilder = NotificationCompat.Builder(this, notificationChannelID)
        val channel = NotificationChannel(
            notificationChannelID,
            "notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager: NotificationManager by lazy {
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        }
        notificationManager.createNotificationChannel(channel)
        notificationBuilder.setContentTitle("Name").setContentText("Entered Name")
            .setSmallIcon(R.drawable.code).setStyle(
                NotificationCompat.BigTextStyle().bigText(text)
            ).priority = NotificationCompat.PRIORITY_MAX
        notificationBuilder.setContentIntent(pendingIntent)
        notificationManager.notify(1, notificationBuilder.build())
    }
}