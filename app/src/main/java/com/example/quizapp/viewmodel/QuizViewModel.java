package com.example.quizapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.model.QuizList;
import com.example.quizapp.repository.QuizRepository;

public class QuizViewModel extends ViewModel {
    QuizRepository repository = new QuizRepository();

    LiveData<QuizList> quizListLiveData;
    public QuizViewModel(){
        quizListLiveData = repository.getQuizFromApi();

    }
    public LiveData<QuizList> getQuizListLiveData(){
        return quizListLiveData;
    }

}
