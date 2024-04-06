package com.example.broadcast.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class SystemStatusReceiver(
    
) : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val  s = "Broadcast: ${intent.action} extras ${intent.extras}"
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
                Log.d("SystemStatusReceiver",s)

        }
    }
}