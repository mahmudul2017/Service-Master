package com.gorentalbd.service.simpleService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorentalbd.service.R

class ServiceActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service1)

        var intent = Intent(this, SimpleService1::class.java)
        startService(intent)
    }
}