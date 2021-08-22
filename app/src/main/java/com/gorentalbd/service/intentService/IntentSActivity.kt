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

        timeChanged()

        liveTime.observe(this, Observer {
            val fajrTime = "18.53"
            val dhurTime = "18.54"
            val asorTime = "18.48"
            val magribTime = "18.49"
            val ishaTime = "18.55"

            Log.d("activityValue", "timeChanged called")

            if (it!! <= fajrTime) {  //ishaTime < value
                tvIntentService.text = "Fajr Time : ${it}"
            } else if (it!! <= dhurTime) {
                tvIntentService.text = "Dhur Time : ${it}"
            } else if (it!! <= asorTime) {
                tvIntentService.text = "Asor Time : ${it}"
            } else if (it!! <= magribTime) {
                tvIntentService.text = "Magrib Time : ${it}"
            } else {
                tvIntentService.text = "Isha Time : ${it}"
            }
        })

        brcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                //val value = intent?.getIntExtra("smallValue", 0)
                val time = intent?.getStringExtra("smallValue")

                if (!time.isNullOrEmpty()) {
                    tvIntentService.visibility = View.VISIBLE

                    //liveTime.value = time

                   /*val fajrTime = "18.39"
                    val dhurTime = "18.40"
                    val asorTime = "18.09"
                    val magribTime = "18.11"
                    val ishaTime = "18.13"

                    if (time!! <= fajrTime) {  //ishaTime < value
                        tvIntentService.text = "Fajr Time : ${time}"
                    } else if (time!! <= dhurTime) {
                        tvIntentService.text = "Dhur Time : ${time}"
                    } else if (time!! <= asorTime) {
                        tvIntentService.text = "Asor Time : ${time}"
                    } else if (time!! <= magribTime) {
                        tvIntentService.text = "Magrib Time : ${time}"
                    } else {
                        tvIntentService.text = "Isha Time : ${time}"
                    }*/
                }
                //tvIntentService.text = value.toString()
                //Log.d("activityValue", "onReceive called")
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