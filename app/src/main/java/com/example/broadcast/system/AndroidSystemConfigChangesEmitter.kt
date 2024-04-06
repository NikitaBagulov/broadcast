package com.example.broadcast.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AndroidSystemConfigChangesEmitter(
    private val context: Context
) : SystemConfigChangesEmitter {
    private val _systemChanges = MutableSharedFlow<SystemChange>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    override val systemChanges: Flow<SystemChange> = _systemChanges.asSharedFlow()

    private val receiver= SystemStatusReceiver()

    override fun subscribeForUpdates() {
        context.registerReceiver(
            /* receiver = */ receiver,
            /* filter = */ IntentFilter().apply {
                addAction(Intent.ACTION_TIME_TICK)
                addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
                addAction(Intent.ACTION_BATTERY_CHANGED)
            }
        )
    }

    override fun unsubscribeFromUpdates() {
        context.unregisterReceiver(receiver)
    }

    private inner class SystemStatusReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            _systemChanges.tryEmit(SystemChange(intent?.action?:"UNKNOWN"))
        }
    }
}