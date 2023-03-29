package com.example.auditiondemo.HandlerDemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.auditiondemo.R;
/*****
 * Looper的初始都是通过ThreadLocal.get()
 * 每个线程都会有一个Looper，而Looper在初始化的时候会创建一个MessageQueue
 * 发送消息会将message，放在消息队列的尾巴，取消息会从队列的头取
 *
 * Looper.loop()是一个死循环，他会不断调用queue.next()从而从中去取消息，取到消息后会通过msg.target.dispatchMessage(msg)
 *
 * 总结，Handler发送消息会将消息保存到Looper维护的消息队列MessageQueue中去，而Looper会死循环一直从队列中取消息，取到消息后会交由Message绑定的Handler回调处理。
 * */
public class HandlerThreadActivity extends AppCompatActivity {
    //以下会造成内存泄漏等问题，应该用静态的Handler类去继承
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                     mTestHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();//start会创建一个新线程，run只是运行
    }

    private Handler mTestHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    System.out.println("收到消息了");
                    break;
            }
        }
    };
}