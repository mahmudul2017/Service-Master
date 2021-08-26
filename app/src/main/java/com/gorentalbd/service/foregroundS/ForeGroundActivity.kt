package com.gorentalbd.service.foregroundS

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gorentalbd.service.R
import com.gorentalbd.service.notification.NotificationActivity
import kotlinx.android.synthetic.main.activity_intentservice.*

class ForeGroundActivity : AppCompatActivity() {
    private var isFajrAlarm = false
    private var isDhurAlarm = false
    private var isAsorAlarm = false
    private var isMagribAlarm = false
    private var isIshaAlarm = false

    companion object {
        var TAG = "ForeGroundActivity"
    }

    // notification
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "android_notification"
    private val notificationId = 101

    // service
    private lateinit var brcastReceiver: BroadcastReceiver
    //private lateinit var broadcastReceiver: MyBrndcastReceiver
    private lateinit var intentFilter: IntentFilter

    // alarm
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)

        /*ringtone = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))*/
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //broadcastReceiver = MyBrndcastReceiver()

         brcastReceiver = object : BroadcastReceiver() {
             override fun onReceive(context: Context?, intent: Intent?) {
                 val time = intent?.getStringExtra("smallValue")

                 if (!time.isNullOrEmpty()) {
                     tvIntentService.visibility = View.VISIBLE

                     val fajrTime = "15:55"
                     val dhurTime = "15:58"
                     val asorTime = "16:00"
                     val magribTime = "17:10"
                     val ishaTime = "17:11"

                     if (time.compareTo(fajrTime) <= 0) {  // time < fajrTime
                         tvIntentService.text = "Fajr Time : ${time}"

                         if (!isFajrAlarm) {
                             createNotification("Fajr", "Fajr Namaz Time")
                             isFajrAlarm = true
                         }
                         Log.d(TAG, "Fajr called $time and isFajrAlarm: $isFajrAlarm")
                     } else if (time.compareTo(dhurTime) <= 0) {
                         tvIntentService.text = "Dhur Time : ${time}"
                         createNotification("Dhur", "Dhur Namaz Time")
                         Log.d(TAG, "Dhur called $time")
                     } else if (time.compareTo(asorTime) <= 0) {
                         tvIntentService.text = "Asor Time : ${time}"
                         createNotification("Asor", "Asor Namaz Time")
                         Log.d(TAG, "Asor called $time")
                     } else if (time.compareTo(magribTime) <= 0) {
                         tvIntentService.text = "Magrib Time : ${time}"
                         createNotification("Magrib", "Magrib Namaz Time")
                         Log.d(TAG, "Magrib called $time")
                     } else if (time!!.compareTo(ishaTime) <= 0) {
                         tvIntentService.text = "Isha Time : ${time}"
                         createNotification("Isha", "Isha Namaz Time")
                         Log.d(TAG, "Isha called $time")
                     } else {
                         tvIntentService.text = "No Time : ${time}"
                         createNotification("No", "No Namaz Time")
                         Log.d(TAG, "No Time called $time")
                     }
                     Log.d(TAG, "onReceive called")
                 }
             }
         }

        intentFilter = IntentFilter(ForeGroundService.FOREGROUND_ACTION)
        //registerReceiver(broadcastReceiver, intentFilter)
        registerReceiver(brcastReceiver, intentFilter)

        val intent = Intent(this, ForeGroundService::class.java)
        ContextCompat.startForegroundService(this, intent)
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }*/

        /*timePicker!!.setOnTimeChangedListener { view, hourOfDay, minute ->
            ServiceCaller(intent)
        }*/
    }

    override fun onResume() {
        intentFilter = IntentFilter(ForeGroundService.FOREGROUND_ACTION)
        //registerReceiver(broadcastReceiver, intentFilter)
        registerReceiver(brcastReceiver, intentFilter)
        super.onResume()
    }

    override fun onDestroy() {
        //unregisterReceiver(broadcastReceiver)
        unregisterReceiver(brcastReceiver)
        super.onDestroy()
    }

    private fun createNotification(title: String, desc: String) {
        val intent = Intent(this, NotificationActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //val title = "Oreo Notification"
            val descText = "Notification Manager"
            notificationChannel =
                NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_HIGH)
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

    private fun ServiceCaller(intent: Intent) {
        //stopService(intent)

        val alarmHour: Int = 12 //timePicker!!.currentHour
        val alarmMinute: Int = 47 //timePicker!!.currentMinute
        Log.d("time", "hour: $alarmHour - minute: $alarmMinute")

        intent.putExtra("alarmHour", alarmHour)
        intent.putExtra("alarmMinute", alarmMinute)

        startService(intent)
    }

    /*class MyBrndcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Boot Outside", Toast.LENGTH_LONG).show()

            val intent = Intent(context, ForeGroundService::class.java)
            if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //context!!.startForegroundService(intent)
                    Toast.makeText(context, "Boot", Toast.LENGTH_LONG).show()
                    ContextCompat.startForegroundService(context!!, intent)
                } else {
                    Toast.makeText(context, "Boot", Toast.LENGTH_LONG).show()
                    ContextCompat.startForegroundService(context!!, intent)
                    //context!!.startService(intent)
                }
            }
        }
    }*/
}