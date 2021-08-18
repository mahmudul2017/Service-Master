@file:Suppress("DEPRECATION")
package com.gorentalbd.service.intentService

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.os.SystemClock
import android.util.Log

class IntentService : IntentService("IntentService") {
    override fun onHandleIntent(intent: Intent?) {
        for (i in 1..100) {
            Log.d("Count", "i value is $i")
            Thread.sleep(1000)
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)

        val restartServicePendingIntent = PendingIntent.getService(
            applicationContext,
            1,
            restartServiceIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val alarmService = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000] =
            restartServicePendingIntent

        super.onTaskRemoved(rootIntent)
    }
}