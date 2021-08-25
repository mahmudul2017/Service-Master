package com.gorentalbd.service.serviceForeg

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ServiceFrgnd: Service() {
    override fun onBind(intent: Intent?): IBinder? {
       return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val thread = Thread(object : Runnable {
            override fun run() {
                Log.d("ServiceFrgnd", "run started")
                var i = 0

                while (i <= 10) {
                    Log.d("ServiceFrgnd", "run running ${i+1}")

                    try {
                        Thread.sleep(1000)
                    } catch (t: InterruptedException) {
                        t.printStackTrace()
                    }
                    i++
                }
                Log.d("ServiceFrgnd", "run complete")
            }
        })
        thread.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ServiceFrgnd", "onDestroy called")
    }
}