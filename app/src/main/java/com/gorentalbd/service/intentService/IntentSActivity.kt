package com.gorentalbd.service.intentService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorentalbd.service.R

class IntentSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intentservice)

        val intent = Intent(this, IntentService::class.java)
        startService(intent)
    }
}