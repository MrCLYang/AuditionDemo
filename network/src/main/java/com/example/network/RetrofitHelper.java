package com.example.network;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    public static String baseUrl;
    private static volatile RetrofitHelper instance;
    private final Retrofit.Builder retrofitBuilder;

    /*** 使用默认BASE_URL*/

    public Retrofit getRetrofit() {
        return getRetrofit(null);
    }

    private Retrofit getRetrofit(String baseUrl) {
        Retrofit retrofit;
        if(TextUtils.isEmpty(baseUrl)){
            retrofit=retrofitBuilder.baseUrl(RetrofitHelper.baseUrl).build();
        }else {
            retrofit=retrofitBuilder.baseUrl(baseUrl).build();
        }
        return retrofit;
    }

    private RetrofitHelper() {
        //baseUrl="https://www.baidu.com";
        baseUrl="http://10.18.31.6:8080/";
        //构建OkHttpClient对象
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        builder.addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(getRequestInterceptor())
                .addInterceptor(getHeaderInterceptor())
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        OkHttpClient okHttpClient = builder.build();

        //创建retrofit 构建者对象
        retrofitBuilder = new Retrofit.Builder();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .build();

        retrofitBuilder.client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    }

    public static RetrofitHelper getInstance(){
        if(instance==null){
            synchronized (RetrofitHelper.class){
                if(instance==null){
                    instance=new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    /**添加公用的头部内容*/
    private Interceptor getHeaderInterceptor(){
        return chain -> {
            Request originalRequest=chain.request();
            Request.Builder builder = originalRequest.newBuilder();
            builder.method(originalRequest.method(),originalRequest.body());
            Request request = builder.build();
            return chain.proceed(request);
        };
    }

    /****添加公用的query参数*/
    private Interceptor getRequestInterceptor(){
        return chain -> {
            Request originalRequest=chain.request();
            //新的参数
            HttpUrl.Builder authorizedUrlBuilder = originalRequest.url()
                    .newBuilder()
                    .scheme(originalRequest.url().scheme())
                    .host(originalRequest.url().host());
            //新的请求
            Request newRequest=originalRequest.newBuilder()
                    .method(originalRequest.method(),originalRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();
            return chain.proceed(newRequest);
        };
    }


}
