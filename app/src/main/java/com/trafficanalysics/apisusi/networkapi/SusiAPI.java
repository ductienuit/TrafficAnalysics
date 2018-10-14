package com.trafficanalysics.apisusi.networkapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trafficanalysics.apisusi.modal.SusiResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SusiAPI {

    @GET("/susi/chat.json")
    Call<JsonObject> getAnswerChatbot(@Query("timezoneOffset") String timezoneOffset,
                                      @Query("q") String query);
}
