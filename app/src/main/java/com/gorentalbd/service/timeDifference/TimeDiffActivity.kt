package com.gorentalbd.service.timeDifference

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_timedifference.*
import java.text.SimpleDateFormat
import java.util.*

class TimeDiffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timedifference)

        /*val date1: Date = Calendar.getInstance().time
        Log.i("app", "Date1 value is: $date1")
        val date2: Date = Calendar.getInstance().time
        Log.i("app", "Date2 value is: $date2")

        if (date1.compareTo(date2) > 0) {
            Log.i("app", "Date1 is after Date2")
        } else if (date1.compareTo(date2) < 0) {
            Log.i("app", "Date1 is before Date2")
        } else if (date1.compareTo(date2) === 0) {
            Log.i("app", "Date1 is equal to Date2")
        }*/

        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        val getCurrentTime: String = sdf.format(c.time)
        val getFajrTime = "12.23"
        val getDuhurTime = "12.24"
        val getAzorTime = "12.25"

        if (getCurrentTime.compareTo(getFajrTime) < 0) { // = getCurrentTime < getFajrTime
            // Do your staff
            tvAlarm.text = "Fajr Time"
            Log.d("Return", "Fojor: getTestTime > getCurrentTime Current: $getCurrentTime Test: $getFajrTime")
        } else if (getCurrentTime.compareTo(getDuhurTime) < 0) {
            tvAlarm.text = "Duhur Time"
            Log.d("Return", "Duhur: getTestTime < getCurrentTime Current: $getCurrentTime Test: $getFajrTime")
        } else if (getCurrentTime.compareTo(getAzorTime) < 0) {
            tvAlarm.text = "Asor Time"
            Log.d("Return", "Asor: getTestTime < getCurrentTime Current: $getCurrentTime Test: $getFajrTime")
        } else {
            tvAlarm.text = "No Time"
            Log.d("Return", "No: No Namz Time")
        }
    }
}