package com.example.broadcast.system

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AndroidSystemConfigChangesEmitter(
    private val context: Context
) : SystemConfigChangesEmitter {
    private val _systemChanges = MutableSharedFlow<SystemChange>()
    override val systemChanges: Flow<SystemChange> = _systemChanges.asSharedFlow()


    override fun subscribeForUpdates() {
        TODO("Not yet implemented")
    }

    override fun unsubscribeFromUpdates() {
        TODO("Not yet implemented")
    }
}