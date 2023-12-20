package com.example.quizapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.model.QuizList;
import com.example.quizapp.service.QuizApi;
import com.example.quizapp.service.QuizInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    QuizApi quizApi;
    public QuizRepository(){
        this.quizApi = new QuizInstance().getQuizInstance();

    }
    public LiveData<QuizList>getQuizFromApi(){
        MutableLiveData<QuizList> data = new MutableLiveData<>();
        Call<QuizList> response = quizApi.getQuiz();
        response.enqueue(new Callback<QuizList>() {
            @Override
            public void onResponse(Call<QuizList> call, Response<QuizList> response) {
                Log.d("CheckData",String.valueOf(response));
                QuizList quizList = response.body();
                data.setValue(quizList);
            }

            @Override
            public void onFailure(Call<QuizList> call, Throwable t) {
                Log.d("CheckDataNull",String.valueOf(t));

            }
        });
        return data;
    }
}
