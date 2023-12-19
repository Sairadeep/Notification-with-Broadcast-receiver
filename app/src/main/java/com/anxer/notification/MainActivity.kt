package com.anxer.notification

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            val intentOnChange = Intent("Data_Changed")
            intentOnChange.putExtra("name", "${mainBinding.editTextText.text}")
            sendBroadcast(intentOnChange)
        }
    }
}