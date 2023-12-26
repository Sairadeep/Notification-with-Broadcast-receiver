package com.anxer.notification

import WorkerM
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.anxer.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        val intent = Intent(this@MainActivity, MyNotificationService::class.java)
        startService(intent)
        mainBinding.buttonSubmit.setOnClickListener {
            val workM = OneTimeWorkRequest.Builder(workerClass = WorkerM::class.java).setConstraints(
                Constraints(NetworkType.CONNECTED)
            ).build()
            WorkManager.getInstance(this).enqueue(workM)
            val intentOnChange = Intent("Data_Changed")
            sendBroadcast(intentOnChange)
        }
    }
}