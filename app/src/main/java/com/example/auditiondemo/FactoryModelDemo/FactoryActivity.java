package com.example.auditiondemo.FactoryModelDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.example.auditiondemo.R;
import com.example.auditiondemo.databinding.ActivityFactoryBinding;

public class FactoryActivity extends BaseActivity<ActivityFactoryBinding>{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_factory;
    }

    @Override
    protected void initView(ActivityFactoryBinding db) {

    }
}