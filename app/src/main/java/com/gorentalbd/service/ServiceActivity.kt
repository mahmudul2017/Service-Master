package com.gorentalbd.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gorentalbd.service.alarmService.AlarmActivity
import com.gorentalbd.service.foregroundS.ForeGroundActivity
import com.gorentalbd.service.intentService.IntentSActivity
import com.gorentalbd.service.notification.NotificationActivity
import com.gorentalbd.service.radioButton.RadioActivity
import com.gorentalbd.service.runPckg.RunActivity
import com.gorentalbd.service.serviceForeg.ServiceFrgActivity
import com.gorentalbd.service.simpleService.ServiceActivity1
import com.gorentalbd.service.timeDifference.TimeDiffActivity
import com.gorentalbd.service.timeformat.TimeFormatActivity
import kotlinx.android.synthetic.main.activity_service.*

class   ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btnSimpleService.setOnClickListener {
            startActivity(Intent(this, ServiceActivity1::class.java))
        }

        btnTimeDifference.setOnClickListener {
            startActivity(Intent(this, TimeDiffActivity::class.java))
        }

        btnIntentService.setOnClickListener {
            startActivity(Intent(this, IntentSActivity::class.java))
        }

        btnForeGroundService.setOnClickListener {
            startActivity(Intent(this, ForeGroundActivity::class.java))
        }

        btnAlarmService.setOnClickListener {
            startActivity(Intent(this, AlarmActivity::class.java))
        }

        btnTimeFormat.setOnClickListener {
            startActivity(Intent(this, TimeFormatActivity::class.java))
        }

        btnRadioButton.setOnClickListener {
            startActivity(Intent(this, RadioActivity::class.java))
        }

        btnRunButton.setOnClickListener {
            startActivity(Intent(this, RunActivity::class.java))
        }

        btnNotification.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        btnServiceFrg.setOnClickListener {
            startActivity(Intent(this, ServiceFrgActivity::class.java))
        }
    }
}