package com.gorentalbd.service.intentService

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import java.text.SimpleDateFormat
import java.util.*


class IntentService: IntentService("IntentService") {
    var mHandler: Handler = Handler()

    companion object {
        final var MY_ACTION = "com.gorentalbd.service"
    }

    override fun onHandleIntent(intent: Intent?) {
        callFunc()
        var k: Int = 0
        /*for (k in 1..100) {
            Log.d("Count", "k value is $k")
            Thread.sleep(1000)

            val c = Calendar.getInstance()
            val sdf = SimpleDateFormat("HH:mm")
            val getCurrentTime: String = sdf.format(c.time)

            val customIntent = Intent(MY_ACTION)
            //intent?.setAction(MY_ACTION)
            val value = customIntent!!.putExtra("smallValue", getCurrentTime)
            sendBroadcast(value)
        }*/
    }

    fun callFunc() {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        val getCurrentTime: String = sdf.format(c.time)

        val customIntent = Intent(MY_ACTION)
        //intent?.setAction(MY_ACTION)
        val value = customIntent!!.putExtra("smallValue", getCurrentTime)
        sendBroadcast(value)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        /*val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)

        val restartServicePendingIntent = PendingIntent.getService(
            applicationContext,
            1,
            restartServiceIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val alarmService = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000] =
            restartServicePendingIntent*/

        super.onTaskRemoved(rootIntent)
    }
}