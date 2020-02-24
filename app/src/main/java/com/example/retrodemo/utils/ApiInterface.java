package com.example.retrodemo.utils;

import android.os.Build;

import com.example.retrodemo.ApiResponses.HeroResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("heroes.php")
    Call<HeroResponse> getHeroes();

}


