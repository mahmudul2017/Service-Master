package com.gorentalbd.service.alarmService

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_alarm.*
import java.util.*

class AlarmActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        /*btnAlarm.setOnClickListener {
            val timeInMilies = 2000
            val intent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("Time", "Current Time is: " + Date().toString())

            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeInMilies,
                System.currentTimeMillis() + timeInMilies.toLong(), pendingIntent)
        }*/

        val timeInMilies: Int = 2000
        val timeInLong: Long = 2000
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("Time", "Current Time is: " + Date().toString())

        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeInMilies, pendingIntent)

        /*alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
            60000, pendingIntent)*/

        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + timeInMilies, // AlarmManager.INTERVAL_HALF_HOUR
            70000,
            pendingIntent
        )
    }

    class AlarmReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("Time", "Alarm Time is: " + Date().toString())
        }
    }
}