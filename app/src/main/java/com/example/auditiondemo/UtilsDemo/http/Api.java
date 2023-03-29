package com.example.auditiondemo.UtilsDemo.http;

import com.example.auditiondemo.UtilsDemo.ResponseModel;
import com.example.auditiondemo.UtilsDemo.model.LoginBackModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("login")
    Observable<ResponseModel<LoginBackModel>> login (@FieldMap Map<String,String> params);
}
