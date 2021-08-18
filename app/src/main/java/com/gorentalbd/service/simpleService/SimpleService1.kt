package com.gorentalbd.service.simpleService

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SimpleService1: Service() {

    companion object {
        val TAG = "SimpleService1"
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        showLog("onCreate called")
        //increament()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showLog("onStartCommand called")
        increament()
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val intent = Intent(applicationContext, ServiceActivity1::class.java)
        sendBroadcast(rootIntent)
        super.onTaskRemoved(rootIntent)
    }

    fun showLog(message: String) {
        Log.d(TAG, message)
    }

    fun increament() {
        for (i: Int in 1..30) {
            showLog("i in ${i.toString()}")
            Thread.sleep(1000)
        }
    }
}