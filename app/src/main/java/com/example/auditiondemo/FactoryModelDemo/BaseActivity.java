package com.example.auditiondemo.FactoryModelDemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**工厂模式创建基类*/
public abstract class BaseActivity extends AppCompatActivity {
    public static Intent createIntent(Context context){
        return new Intent(context,BaseActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(getLayoutResId());
        //初始化视图
        initView();
    }

    //抽象方法，子类必须实现以提供布局资源ID
    protected  abstract int getLayoutResId();
    //抽象方法，子类必须实现初始化视图
    protected abstract void initView();
}
