package com.example.auditiondemo.ServiceDemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.auditiondemo.R

/*TODO 前台服务展示界面  also 是附加操作，apply是对当前对象做一个初始化处理
*/
class ForegroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground)
        Intent(this,ForegroundService::class.java).also {
            startService(it)
        }
    }
}