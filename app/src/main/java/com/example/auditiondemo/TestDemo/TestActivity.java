package com.example.auditiondemo.TestDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestFutureTarget;
import com.example.auditiondemo.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        initRetrofit();
        initGlide();
        SingleInstance singleInstance = SingleInstance.getSingleInstance();//双重锁单例
    }

    private void initGlide() {
        Glide.with(getApplicationContext())
                .load("www.baidu.com");
    }

    private void initRetrofit() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetApi getApi = retrofit.create(GetApi.class);
        retrofit2.Call<String> call = getApi.getCall("yang", "nv");
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {

            }
        });
    }

    private void init() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(2,TimeUnit.SECONDS)
                .writeTimeout(2,TimeUnit.SECONDS)
                .build();
        Request request=new Request.Builder()
                .url("www.baidu.com")
                .addHeader("","")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });


    }


}