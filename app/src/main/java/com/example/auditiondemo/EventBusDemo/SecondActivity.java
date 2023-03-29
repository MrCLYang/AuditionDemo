package com.example.auditiondemo.EventBusDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.auditiondemo.R;
import com.example.auditiondemo.databinding.ActivitySecondBinding;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_second);
        binding.btnSecondPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("Hello EventBus!"));
            }
        });
    }
}