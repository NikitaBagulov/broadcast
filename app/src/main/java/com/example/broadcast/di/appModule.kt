package com.example.broadcast.di

import com.example.broadcast.MainViewModel
import com.example.broadcast.system.AndroidSystemConfigChangesEmitter
import com.example.broadcast.system.SystemConfigChangesEmitter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    factory<SystemConfigChangesEmitter>{
        AndroidSystemConfigChangesEmitter(get())
    }
    viewModel{MainViewModel(get())}
}