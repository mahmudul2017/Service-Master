package com.gorentalbd.service.bootComplete

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.gorentalbd.service.foregroundS.ForeGroundService


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val mSharedPref = context!!.getSharedPreferences("general", MODE_PRIVATE)

        if (intent != null && intent.action != null) {
            if (Intent.ACTION_BOOT_COMPLETED == intent.action || Intent.ACTION_LOCKED_BOOT_COMPLETED == intent.action) {
                Log.d("BootReceiver", "boot called inside")
                val intent = Intent(context, ForeGroundService::class.java)
                ContextCompat.startForegroundService(context, intent)
            }
        }

        /*if (intent!!.action == Intent.ACTION_BOOT_COMPLETED) {
            val i = Intent(context, ServiceActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)

            Log.d("BootReceiver", "boot called inside")
        }*/
    }
}