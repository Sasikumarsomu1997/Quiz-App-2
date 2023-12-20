package com.example.quizapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizInstance {
    private static Retrofit retrofit =null;
    private  static String BASE_URL ="http://10.0.2.2/myapi/";

    public static QuizApi getQuizInstance() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();


        }

        return retrofit.create(QuizApi.class);
    }
}
