package com.gorentalbd.service.radioButton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_radio.*

class RadioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio)

        radio.setOnCheckedChangeListener { group, checkedId ->
            val value = findViewById<RadioButton>(checkedId)
            Log.d("radio", value.text.toString())
        }
    }
}