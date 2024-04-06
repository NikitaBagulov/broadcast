package com.example.broadcast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.broadcast.system.SystemConfigChangesEmitter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val systemConfigChangesEmitter: SystemConfigChangesEmitter
): ViewModel() {
    private val _displayText = MutableLiveData<String>()
    val displayText: LiveData<String> =_displayText

    init{
        viewModelScope.launch {
            systemConfigChangesEmitter.subscribeForUpdates()
            systemConfigChangesEmitter.systemChanges.collectLatest {
                _displayText.value = it.action
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        systemConfigChangesEmitter.unsubscribeFromUpdates()
    }

}