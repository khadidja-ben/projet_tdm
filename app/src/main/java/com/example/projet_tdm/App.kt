package com.example.projet_tdm

import android.app.Application
import com.example.projet_tdm.roomdao.RoomService

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}