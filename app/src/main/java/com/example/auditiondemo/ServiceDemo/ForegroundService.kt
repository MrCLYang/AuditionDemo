package com.example.auditiondemo.ServiceDemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.auditiondemo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val CHANNEL_ID="my notification channel"
class ForegroundService :LifecycleService() {


    private var number=0;
    override fun onCreate() {
        super.onCreate()
        lifecycleScope.launch {
            while (true){
                delay(1000)
                Log.d("hello", "onCreate: ${number++}")
            }
        }
        createChannel()
       // val notification=NotificationCompat.Builder(this, "123")
        val pendingIntent=PendingIntent.getActivity(this,
                0,
                Intent(this,ForegroundActivity::class.java),
                0
            )
        val notification=NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_ac_unit_24)
            .setContentTitle("This is title")
            .setContentText("this is a notification")
            .setContentIntent(pendingIntent)//通过通知栏进入到当前activity
            .build()
        //TODO 在Android  8的时候可以这样写，但是以上就需要其他方式创建 channelld 不能以随机写
      //  startForeground(1,notification)
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        TODO("Return the communication channel to the service.")
    }

    private fun createChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name="my notification channel NO.1"
            val importance=NotificationManager.IMPORTANCE_DEFAULT
            val mChannel=NotificationChannel(CHANNEL_ID,name,importance)
            val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}