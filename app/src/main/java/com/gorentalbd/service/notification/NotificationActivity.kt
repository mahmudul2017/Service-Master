package com.gorentalbd.service.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_notification.*
import java.text.SimpleDateFormat
import java.util.*

class NotificationActivity : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "android_notification"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val currentTime = getCurrentTime()
        val time = "16:54"

        if (currentTime > time) {
            createNotification()
        }

        btnSendNotify.setOnClickListener {
            createNotification()
        }
    }

    private fun getCurrentTime() : String {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        val getCurrentTime: String = sdf.format(c.time)
        return getCurrentTime
    }

    private fun createNotification() {
        val intent = Intent(this, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val title = "Oreo Notification"
            val descText = "Notification Manager"
            notificationChannel = NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(descText)
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.flutter
                    )
                )
                .setContentIntent(pendingIntent)
        } else {
            builder = Notification.Builder(this)
                .setContentTitle("Namaz Notification")
                .setContentText("Amol Notification for you")
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.flutter
                    )
                )
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(notificationId, builder.build())
    }
}