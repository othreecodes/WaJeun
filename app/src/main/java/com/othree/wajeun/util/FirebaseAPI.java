package com.othree.wajeun.util;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by root on 2/15/17.
 */

public interface FirebaseAPI {

    @Headers({"Content-Type: application/json","Authorization:key=AIzaSyDtWmPkYPtq8ysblWdcQTzufCUYx96AKVs"})
    @POST("/fcm/send")
    Call<Object> send(@Body FCMDataMessage message);



}
