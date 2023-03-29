package com.example.auditiondemo.UtilsDemo.http;

import androidx.lifecycle.LifecycleOwner;

import com.example.auditiondemo.UtilsDemo.HttpCall;
import com.example.auditiondemo.UtilsDemo.ResponseModel;
import com.example.auditiondemo.UtilsDemo.RetrofitHelper;
import com.example.auditiondemo.UtilsDemo.listener.CallBackList;
import com.example.auditiondemo.UtilsDemo.model.LoginBackModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class HttpRequest {
    public static void login(LifecycleOwner owner, String loginName, String pwd, CallBackList<LoginBackModel> callBackList){
        Map<String,String> params=new HashMap<>();
        params.put("loginName",loginName);
        params.put("password",pwd);
        Observable<ResponseModel<LoginBackModel>> observable= RetrofitHelper
                .getInstance()
                .getRetrofit()
                .create(Api.class)
                .login(params);
        HttpCall.doCall(owner,observable,callBackList,null);
    }
}
