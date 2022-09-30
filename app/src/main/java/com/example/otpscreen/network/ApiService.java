package com.example.otpscreen.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiService {
    @POST("send")
    Call<String> sendremotemessage(
            @HeaderMap HashMap<String, String> headers,
            @Body String remotebody
    );
}
