package com.gorentalbd.service.foregroundS

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_foreground.*

class ForeGroundActivity : AppCompatActivity() {
    //private val timePicker: TimePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)

        //ActivityCompat.requestPermissions(this, String[]{Manifest.permission.FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED)

        val intent = Intent(this, ForeGroundService::class.java)
        ServiceCaller(intent)


        timePicker!!.setOnTimeChangedListener { view, hourOfDay, minute ->
            //ServiceCaller(intent)
        }
    }

    private fun ServiceCaller(intent: Intent) {
        stopService(intent)

        val alarmHour: Int = 12 //timePicker!!.currentHour
        val alarmMinute: Int = 47 //timePicker!!.currentMinute
        Log.d("time", "hour: $alarmHour - minute: $alarmMinute")

        intent.putExtra("alarmHour", alarmHour)
        intent.putExtra("alarmMinute", alarmMinute)

        startService(intent)
    }
}