package com.example.auditiondemo.ViewModelDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.auditiondemo.R;

public class ViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        MyViewModel myViewModel= new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyViewModel.class);
        myViewModel.getName().setValue("ya");
        myViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
    }
}