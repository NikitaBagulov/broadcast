package com.example.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.example.broadcast.system.SystemStatusReceiver

class MainActivity : AppCompatActivity() {
    private val systemStatusReceiver = SystemStatusReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        registerReceiver(systemStatusReceiver, IntentFilter().apply{
            addAction(Intent.ACTION_TIME_TICK)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_CHANGED)
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val currentStatusTextView = findViewById<TextView>(R.id.currentStatus)
        currentStatusTextView.text = intent?.extras?.getString("action")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemStatusReceiver)
    }

}