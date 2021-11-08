package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var etNotification: EditText
    lateinit var btnNotification: Button
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    private var notificationId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etNotification = findViewById(R.id.etNotification)
        btnNotification = findViewById(R.id.btnNotification)
        btnNotification.setOnClickListener{
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, MainActivity2::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
                var builder0 = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Your Notification")
                    .setContentText("${etNotification.text}")
                notificationManager.notify(notificationId, builder0.build())
            } else {}
        }
    }
}