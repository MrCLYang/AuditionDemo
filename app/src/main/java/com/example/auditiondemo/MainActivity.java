package com.example.auditiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put(null,"a");
        Hashtable<String ,String> hashtable=new Hashtable<>();
        

    }
}