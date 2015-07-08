package com.anotherdev.stackapp.api.stackexchange;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface StackOverflowApi {

    String BASE_URL = "https://api.stackexchange.com/2.2";


    @GET("/questions?order=desc&sort=activity&site=stackoverflow&filter=withbody&pagesize=100")
    Observable<Questions> questions();

    @GET("/search?order=desc&sort=activity&site=stackoverflow&filter=withbody&pagesize=100")
    Observable<Questions> search(@Query("intitle") String text);


    @GET("/questions/{question_id}/answers?order=desc&sort=votes&site=stackoverflow&filter=withbody")
    Observable<Answers> answers(@Path("question_id") String questionId);
}
