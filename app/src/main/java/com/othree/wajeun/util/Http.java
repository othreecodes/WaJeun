package com.othree.wajeun.util;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 2/15/17.
 */

public class Http {


    public Http(){

    }

    Call<Object> send(FCMDataMessage dataMessage){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com")//url of FCM message server
                .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                .build();

        FirebaseAPI API = retrofit.create(FirebaseAPI.class);
        return API.send(dataMessage);

    }

}
