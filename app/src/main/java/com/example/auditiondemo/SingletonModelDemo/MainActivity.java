package com.example.auditiondemo.SingletonModelDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.auditiondemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Singleton instance = Singleton.getInstance();

    }
}