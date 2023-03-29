package com.example.auditiondemo.RetrofitDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.auditiondemo.R;
import com.example.auditiondemo.databinding.ActivityRetrofitBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    ActivityRetrofitBinding binding;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_retrofit);
        //构建Retrofit 实例
        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.uomg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });


    }

    private void init() {
        //创建网络请求接口实例对象
        Api api = retrofit.create(Api.class);
        //发送请求进行封装
        Call<Data<Info>> dataCall = api.getJsonData("新歌榜", "json");
        dataCall.enqueue(new Callback<Data<Info>>() {
            @Override
            public void onResponse(Call<Data<Info>> call, Response<Data<Info>> response) {
                Toast.makeText(RetrofitActivity.this, "get回调成功：异步执行", Toast.LENGTH_SHORT).show();
                Data<Info> body=response.body();
                if(body==null) return;
                Info data = body.getData();
                if(data==null)return;
                binding.tv.setText("返回的数据："+"\n\n"+data.getName()+ "\n" + data.getPictureUrl());
            }

            @Override
            public void onFailure(Call<Data<Info>> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "get回调失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}