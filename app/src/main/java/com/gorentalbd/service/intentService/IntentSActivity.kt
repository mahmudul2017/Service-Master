package com.gorentalbd.service.intentService

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_intentservice.*
import java.text.SimpleDateFormat
import java.util.*

class IntentSActivity : AppCompatActivity() {
    private val liveTime = MutableLiveData<String>()

    private lateinit var brcastReceiver: BroadcastReceiver
    private lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intentservice)

        brcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                //val value = intent?.getIntExtra("smallValue", 0)
                val time = intent?.getStringExtra("smallValue")

                if (!time.isNullOrEmpty()) {
                    tvIntentService.visibility = View.VISIBLE

                    val fajrTime = "12:32"
                    val dhurTime = "12:34"
                    val asorTime = "12:36"
                    val magribTime = "12:38"
                    val ishaTime = "12:40"

                    if (time.compareTo(fajrTime) <= 0) { //time < fajrTime
                        tvIntentService.text = "Fajr Time : ${time}"
                        Log.d("activityValue", "Fajr called $time")
                    } else if (time.compareTo(dhurTime) <= 0) {
                        tvIntentService.text = "Dhur Time : ${time}"
                        Log.d("activityValue", "Dhur called $time")
                    } else if (time.compareTo(asorTime) <= 0) {
                        tvIntentService.text = "Asor Time : ${time}"
                        Log.d("activityValue", "Asor called $time")
                    } else if (time.compareTo(magribTime) <= 0) {
                        tvIntentService.text = "Magrib Time : ${time}"
                        Log.d("activityValue", "Magrib called $time")
                    } else if (time!!.compareTo(ishaTime) <= 0) {
                        tvIntentService.text = "Isha Time : ${time}"
                        Log.d("activityValue", "Isha called $time")
                    } else {
                        tvIntentService.text = "No Time : ${time}"
                        Log.d("activityValue", "No Time called $time")
                    }
                    //tvIntentService.text = value.toString()
                    Log.d("activityValue", "onReceive called")
                }
            }
        }

        intentFilter = IntentFilter(IntentService.MY_ACTION)
        //intentFilter.addAction(IntentService.MY_ACTION)
        registerReceiver(brcastReceiver, intentFilter)

        val intent = Intent(this, IntentService::class.java)
        startService(intent)
    }

    fun timeChanged() {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        val getCurrentTime: String = sdf.format(c.time)
        liveTime.value = getCurrentTime
    }

    /*override fun onStart() {
        intentFilter = IntentFilter()
        intentFilter.addAction(IntentService.MY_ACTION)
        registerReceiver(brcastReceiver, intentFilter)
        super.onStart()
    }*/

    override fun onResume() {
        intentFilter = IntentFilter(IntentService.MY_ACTION)
        //intentFilter.addAction(IntentService.MY_ACTION)
        registerReceiver(brcastReceiver, intentFilter)
        super.onResume()
    }

    override fun onDestroy() {
        unregisterReceiver(brcastReceiver)
        super.onDestroy()
    }
    /*class broadcastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val value = intent?.getIntExtra("smallValue", 0)
        }
    }*/
}