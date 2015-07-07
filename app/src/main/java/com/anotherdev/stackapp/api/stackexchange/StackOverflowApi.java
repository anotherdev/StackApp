package com.anotherdev.stackapp.api.stackexchange;

import retrofit.http.GET;
import rx.Observable;

public interface StackOverflowApi {

    String BASE_URL = "https://api.stackexchange.com/2.2";


    @GET("/questions?order=desc&sort=activity&site=stackoverflow")
    Observable<Questions> questions();
}
