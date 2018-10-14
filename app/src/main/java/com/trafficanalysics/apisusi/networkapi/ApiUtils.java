package com.trafficanalysics.apisusi.networkapi;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api.susi.ai/";

    public static SusiAPI getAPIService() {

        return RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(SusiAPI.class);
    }
}
