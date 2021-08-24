package com.gorentalbd.service.intentService

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gorentalbd.service.R
import com.gorentalbd.service.alarmService.AlarmActivity
import com.gorentalbd.service.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_intentservice.*
import java.text.SimpleDateFormat
import java.util.*

class IntentSActivity : AppCompatActivity() {
    // notification
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "android_notification"
    private val notificationId = 101

    // service
    private lateinit var brcastReceiver: BroadcastReceiver
    private lateinit var intentFilter: IntentFilter

    // alarm
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intentservice)

        /*ringtone = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(
            RingtoneManager.TYPE_RINGTONE))*/
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        brcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val time = intent?.getStringExtra("smallValue")

                if (!time.isNullOrEmpty()) {
                    tvIntentService.visibility = View.VISIBLE

                    val fajrTime = "17:04"
                    val dhurTime = "17:06"
                    val asorTime = "17:08"
                    val magribTime = "17:10"
                    val ishaTime = "17:11"

                    if (time.compareTo(fajrTime) <= 0) {  // time < fajrTime
                        tvIntentService.text = "Fajr Time : ${time}"
                        createNotification("Fajr", "Fajr Namaz Time")
                        Log.d("activityValue", "Fajr called $time")
                    } else if (time.compareTo(dhurTime) <= 0) {
                        tvIntentService.text = "Dhur Time : ${time}"
                        createNotification("Dhur", "Dhur Namaz Time")
                        Log.d("activityValue", "Dhur called $time")
                    } else if (time.compareTo(asorTime) <= 0) {
                        tvIntentService.text = "Asor Time : ${time}"
                        createNotification("Asor", "Asor Namaz Time")
                        Log.d("activityValue", "Asor called $time")
                    } else if (time.compareTo(magribTime) <= 0) {
                        tvIntentService.text = "Magrib Time : ${time}"
                        createNotification("Magrib", "Magrib Namaz Time")
                        Log.d("activityValue", "Magrib called $time")
                    } else if (time!!.compareTo(ishaTime) <= 0) {
                        tvIntentService.text = "Isha Time : ${time}"
                        createNotification("Isha", "Isha Namaz Time")
                        Log.d("activityValue", "Isha called $time")
                    } else {
                        tvIntentService.text = "No Time : ${time}"
                        createNotification("No", "No Namaz Time")
                        Log.d("activityValue", "No Time called $time")
                    }
                    Log.d("activityValue", "onReceive called")
                }
            }
        }

        intentFilter = IntentFilter(IntentService.MY_ACTION)
        registerReceiver(brcastReceiver, intentFilter)

        val intent = Intent(this, IntentService::class.java)
        startService(intent)

        val timeInMilies: Int = 2000
        val timeInLong: Long = 2000
        val alarmIntent = Intent(this, AlarmMngReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("Time", "Current Time is: " + Date().toString())

        alarmManager?.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            SystemClock.elapsedRealtime() + timeInMilies, // AlarmManager.INTERVAL_HALF_HOUR
            70000,
            pendingIntent
        )
    }

    class AlarmMngReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var ringtone: Ringtone? = null
            ringtone = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
            ringtone!!.play()
            Thread.sleep(10000)
            ringtone.stop()
            Log.d("alarm", "Alarm Time is: " + Date().toString())
        }
    }

    override fun onResume() {
        intentFilter = IntentFilter(IntentService.MY_ACTION)
        registerReceiver(brcastReceiver, intentFilter)
        super.onResume()
    }

    override fun onDestroy() {
        unregisterReceiver(brcastReceiver)
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