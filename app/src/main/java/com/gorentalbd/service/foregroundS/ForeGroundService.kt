package com.gorentalbd.service.foregroundS

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.gorentalbd.service.R
import com.gorentalbd.service.intentService.IntentService
import com.gorentalbd.service.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_intentservice.*
import java.text.SimpleDateFormat
import java.util.*

class ForeGroundService: Service() {
    // notification
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "android_notification"
    private val notificationId = 101

    private var isFajrAlarm = false
    private var isDhurAlarm = false
    private var isAsorAlarm = false
    private var isMagribAlarm = false
    private var isIshaAlarm = false

    companion object {
        var FOREGROUND_ACTION = "com.gorentalbd.service.foregroundS"
        var TAG = "ForeGroundService"
    }

    private var alarmHour: Int? = null
    private var alarmMinute: Int? = null
    private var ringtone: Ringtone? = null
    private val t: Timer = Timer()

    private val CHANNEL_ID: String = "MyNotificationChannelID"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        alarmHour = intent?.getIntExtra("alarmHour", 0)
        alarmMinute = intent?.getIntExtra("alarmMinute", 0)

        ringtone = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        try {
            val notificationIntent = Intent(this, ForeGroundActivity::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

            val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
                 .setContentTitle("My Alarm clock")
                 .setContentText("Alarm time – " + alarmHour.toString() + " : " + alarmMinute.toString())
                 .setSmallIcon(R.drawable.ic_launcher_foreground)
                 .setContentIntent(pendingIntent)
                 .build()

            startForeground(1, notification)

            val notificationChannel = NotificationChannel(CHANNEL_ID, "My Alarm clock Service", NotificationManager.IMPORTANCE_NONE)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(notificationChannel)
        } catch (e: Exception){
            e.printStackTrace()
        }

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        Log.d("Count", "service call check")
        t.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val c = Calendar.getInstance()
                val sdf = SimpleDateFormat("HH:mm")
                val getCurrentTime: String = sdf.format(c.time)

                val fajrTime = "15:55"
                val dhurTime = "15:58"
                val asorTime = "16:00"
                val magribTime = "17:10"
                val ishaTime = "17:11"

                if (getCurrentTime.compareTo(fajrTime) <= 0) {  // time < fajrTime
                    if (!isFajrAlarm) {
                        createNotification("Fajr", "Fajr Namaz Time")
                        isFajrAlarm = true
                    }
                    Log.d(TAG, "Fajr called $getCurrentTime and isFajrAlarm: $isFajrAlarm")
                } else if (getCurrentTime.compareTo(dhurTime) <= 0) {
                    createNotification("Dhur", "Dhur Namaz Time")
                    Log.d(TAG, "Dhur called $getCurrentTime")
                } else if (getCurrentTime.compareTo(asorTime) <= 0) {
                    createNotification("Asor", "Asor Namaz Time")
                    Log.d(TAG, "Asor called $getCurrentTime")
                } else if (getCurrentTime.compareTo(magribTime) <= 0) {
                    createNotification("Magrib", "Magrib Namaz Time")
                    Log.d(TAG, "Magrib called $getCurrentTime")
                } else if (getCurrentTime!!.compareTo(ishaTime) <= 0) {
                    createNotification("Isha", "Isha Namaz Time")
                    Log.d(TAG, "Isha called $getCurrentTime")
                } else {
                    createNotification("No", "No Namaz Time")
                    Log.d(TAG, "No Time called $getCurrentTime")
                }

                val customIntent = Intent(FOREGROUND_ACTION)
                val value = customIntent!!.putExtra("smallValue", getCurrentTime)
                sendBroadcast(value)
                Log.d(TAG, "service called")
            }
        }, 0, 5000)

        Log.d(TAG, "service outside called")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun createNotification(title: String, desc: String) {
        val intent = Intent(this, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //val title = "Oreo Notification"
            val descText = "Notification Manager"
            notificationChannel = NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(desc)
                .setAutoCancel(true)
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
                .setContentTitle(title)
                .setContentText(desc)
                .setAutoCancel(true)
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