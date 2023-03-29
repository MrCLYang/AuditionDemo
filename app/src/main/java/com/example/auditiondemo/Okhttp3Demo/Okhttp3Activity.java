package com.example.auditiondemo.Okhttp3Demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.auditiondemo.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp3Activity extends AppCompatActivity {

    /**  通过builder是建造者模式创建出来的*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp3);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2,TimeUnit.SECONDS)
                .readTimeout(2,TimeUnit.SECONDS)
                .build();
        //OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        //创建线程，在子线程中运行
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        //TODO  子线程中有Looper，但Looper没启动，Looper.prepare()是启用Looper,而Looper.loop是启动遍历，
                        //TODO 子线程中实现Handler也需要启动Looper，一个线程中有一个Looper和MessageQuene
                        Looper.prepare();
                        Toast.makeText(Okhttp3Activity.this, "响应成功", Toast.LENGTH_SHORT).show();
                        Log.d("Hello", "onResponse: "+response);
                        Looper.loop();
                    }
                });
            }
        }).start();

    }
}