package com.example.auditiondemo.ServiceDemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.auditiondemo.R
import com.example.auditiondemo.databinding.ActivityServiceBinding
import java.util.concurrent.Executor

class ServiceActivity : AppCompatActivity() {
    //lateinit 是延迟初始化，即告诉编译器，在开始别给我检测，在使用这个变量之前我会初始化好，late init
    private lateinit var binding:ActivityServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        binding= DataBindingUtil.setContentView<ActivityServiceBinding>(this,R.layout.activity_service)
    }

    override fun onStart() {
        super.onStart()
        binding.buttonStartService.setOnClickListener {
            Intent(this,MyService::class.java).also {
                startService(it)
            }

        }

        binding.buttonBindService.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            val connection= object: ServiceConnection{
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    (service as MyService.MyBinder).service.numberLiveData.observe(this@ServiceActivity,
                        Observer {
                            binding.textView.text="$it"
                        })
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                }

            }
            bindService(intent,connection, BIND_AUTO_CREATE)
        }



    }

    override fun onStop() {
        super.onStop()
        Intent(this,MyService::class.java).also {
            stopService(it)
        }
    }
}