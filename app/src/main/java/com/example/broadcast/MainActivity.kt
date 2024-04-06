package com.example.broadcast

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val statusView = findViewById<TextView>(R.id.currentStatus)
        mainViewModel.displayText.observe(this){
            statusView.text = it
        }

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            val intent = Intent("com.example.message")
            intent.putExtra("text", "Hello World!")
            sendBroadcast(intent)
//            val intentFilter = IntentFilter("com.example.message")
//            registerReceiver()
            // TODO add to broadcast receiver new intent filter for action specified above
        }

    }

}