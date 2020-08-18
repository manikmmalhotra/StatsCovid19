package com.jerry.statscovid19;

import com.jerry.statscovid19.Pojo.Stats;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("summary")
    Call <Stats> statslist();

}
