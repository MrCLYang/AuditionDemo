package com.example.auditiondemo.RetrofitDemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    //get
    @GET("api/rand.music")
    Call<Data<Info>> getJsonData(@Query("sort") String sort, @Query("format") String format);
}
