package com.example.auditiondemo.UtilsDemo.utils;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HttpUtils {
    /**Post请求，获取请求体*/
    public static RequestBody getRequestBody(Map<String,Object> params){
        JSONObject jsonObject=new JSONObject(params);
        return RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
    }

    /** Post 请求，获取请求体*/
    public static RequestBody getRequestBody(String params){
        return RequestBody.create(MediaType.parse("application/json"),params);
    }
}
