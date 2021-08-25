package com.gorentalbd.service.serviceForeg

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.gorentalbd.service.R

class ServiceFrgActivity : AppCompatActivity() {
    private lateinit var tvForeGrnd: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicefrg)

        tvForeGrnd = findViewById(R.id.tvForeGrnd)
        val myForeReceiver = MyForeReceiver()
        val dataPass = myForeReceiver.resultData
        Log.d("data", dataPass)

        val intent = Intent(this, ServiceFrgnd::class.java)
        startService(intent)
    }

    class MyForeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
           val data = "VALUE PASS"
        }
    }
}