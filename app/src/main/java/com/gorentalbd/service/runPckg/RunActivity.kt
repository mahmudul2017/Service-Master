package com.gorentalbd.service.runPckg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.gorentalbd.service.R
import kotlinx.android.synthetic.main.activity_run.*
import java.util.*

class RunActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run)

        btnRunStart.setOnClickListener {
            val handler = Handler()
            handler.postDelayed({
                codeCalled()
                Log.d("run", "called inside run" + Calendar.getInstance().time)
            }, 1000)
        }
    }

    private fun codeCalled() {
        Toast.makeText(this, "called outside run", Toast.LENGTH_LONG).show()
        Log.d("run", "called outside run" + Calendar.getInstance().time)
    }
}