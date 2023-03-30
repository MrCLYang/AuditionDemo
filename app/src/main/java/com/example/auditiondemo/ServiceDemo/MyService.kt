package com.example.auditiondemo.ServiceDemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : LifecycleService() {

    private var number=0;
    val numberLiveData=MutableLiveData(0)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("hello", "onStartCommand: Service")
        lifecycleScope.launch {
            while (true){
                delay(1000)
                Log.d("hello", "onStartCommand: ${number++}")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        lifecycleScope.launch {
            while (true){
                delay(1000)
                numberLiveData.value=numberLiveData.value?.plus(1)
            }
        }

        return MyBinder()
    }

    //inner  使内部类变成非静态的内部类了，从而可以调用外部类了，不加编译后就是静态内部类
   inner class MyBinder : Binder() {
        val service=this@MyService
   }

}