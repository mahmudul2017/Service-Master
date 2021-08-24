package com.gorentalbd.service.foregroundS

import android.app.*
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.gorentalbd.service.R
import java.util.*

class ForeGroundService: Service() {
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

        /*try {
            val notificationIntent: Intent = Intent(this, ForeGroundActivity::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

            val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
                 .setContentTitle("My Alarm clock")
                 .setContentText("Alarm time – " + alarmHour.toString() + " : " + alarmMinute.toString())
                 .setSmallIcon(R.drawable.ic_launcher_foreground)
                 .setContentIntent(pendingIntent)
                 .build()

            startForeground(1, notification)
            //startForegroundService(intent)

            val notificationChannel = NotificationChannel(CHANNEL_ID, "My Alarm clock Service", NotificationManager.IMPORTANCE_NONE)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(notificationChannel)

            for (i in 1..100) {
                Log.d("Count", "i value is $i")
                Thread.sleep(1000)
            }
        }
        catch (e: Exception){
            e.printStackTrace()
        }*/

        /*t.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (Calendar.getInstance().time.hours === alarmHour &&
                    Calendar.getInstance().time.minutes === alarmMinute
                ) {
                    ringtone!!.play()
                } else {
                    ringtone!!.stop()
                }
            }
        }, 0, 2000)*/

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        ringtone!!.stop()
        t.cancel()
        super.onDestroy()
    }
}