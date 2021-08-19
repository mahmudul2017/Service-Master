package com.gorentalbd.service.timeformat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorentalbd.service.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeFormatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeformat)

        /*val time = "23:15"

        try {
            val sdf = SimpleDateFormat("H:mm")
            val dateObj: Date = sdf.parse(time)
            System.out.println(dateObj)
            System.out.println(SimpleDateFormat("K:mm").format(dateObj))
        } catch (e: ParseException) {
            e.printStackTrace()
        }*/
        val _24HourTime = "02:15"
        getTimeFormat(_24HourTime)
    }

    private fun getTimeFormat(time: String) {
        try {
            val _24HourSDF = SimpleDateFormat("HH:mm")
            val _12HourSDF = SimpleDateFormat("hh:mm a")
            val _24HourDt = _24HourSDF.parse(time)
            //System.out.println(_24HourDt)
            println(_12HourSDF.format(_24HourDt))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}