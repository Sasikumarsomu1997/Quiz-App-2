package com.example.quizapp.service;

import com.example.quizapp.model.QuizList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizApi {
    @GET("quizapp.php")
    Call<QuizList> getQuiz();

}
