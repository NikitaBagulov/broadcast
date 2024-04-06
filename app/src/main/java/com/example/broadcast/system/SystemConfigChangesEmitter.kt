package com.example.broadcast.system

import kotlinx.coroutines.flow.Flow

interface SystemConfigChangesEmitter {
    val systemChanges: Flow<SystemChange>

    fun subscribeForUpdates()
    fun unsubscribeFromUpdates()
}

data class SystemChange(
    val action: String
)