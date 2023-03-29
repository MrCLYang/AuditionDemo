package com.example.auditiondemo.TestDemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetApi {
    @GET("api/rand.music")
    Call<String> getCall(@Query("name")String name,@Query("sex")String sex);
}
