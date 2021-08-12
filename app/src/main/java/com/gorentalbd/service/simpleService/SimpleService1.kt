package com.gorentalbd.service.simpleService

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SimpleService1 : Service() {

    companion object {
        val TAG = "SimpleService1"
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        showLog("onCreate called")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showLog("onStartCommand called")

        for (i: Int in 1..10) {
            showLog("i in ${i.toString()}")
            Thread.sleep(1000)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun showLog(message: String) {
        Log.d(TAG, message)
    }
}