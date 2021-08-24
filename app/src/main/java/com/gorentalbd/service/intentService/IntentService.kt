package com.gorentalbd.service.intentService

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class IntentService: IntentService("IntentService") {
    private val t: Timer = Timer()

    companion object {
        var MY_ACTION = "com.gorentalbd.service"
    }

    override fun onHandleIntent(intent: Intent?) {
        t.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val c = Calendar.getInstance()
                val sdf = SimpleDateFormat("HH:mm")
                val getCurrentTime: String = sdf.format(c.time)

                val customIntent = Intent(MY_ACTION)
                val value = customIntent!!.putExtra("smallValue", getCurrentTime)
                sendBroadcast(value)
                Log.d("Count", "service called")
            }
        }, 0, 5000)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
    }
}