package com.gorentalbd.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorentalbd.service.simpleService.ServiceActivity1
import com.gorentalbd.service.timeDifference.TimeDifferenceActivity
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btnSimpleService.setOnClickListener {
            startActivity(Intent(this, ServiceActivity1::class.java))
        }

        btnTimeDifference.setOnClickListener {
            startActivity(Intent(this, TimeDifferenceActivity::class.java))
        }
    }
}