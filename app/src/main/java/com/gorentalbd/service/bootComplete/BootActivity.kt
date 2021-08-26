package com.gorentalbd.service.bootComplete

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorentalbd.service.R


class BootActivity : AppCompatActivity() {
    //private lateinit var bootReceiver: BootReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot)

        //bootReceiver = BootReceiver()
        //changeBootStateReceiver(this, false)
    }

    override fun onResume() {
        //registerReceiver(bootReceiver, IntentFilter(Intent.ACTION_BOOT_COMPLETED))
        super.onResume()
    }

    override fun onDestroy() {
        //unregisterReceiver(bootReceiver)
        super.onDestroy()
    }

    private fun changeBootStateReceiver(context: Context, enable: Boolean) {
        val receiver = ComponentName(context, BootReceiver::class.java)
        val pm: PackageManager = context.getPackageManager()
        pm.setComponentEnabledSetting(
            receiver,
            if (enable) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}