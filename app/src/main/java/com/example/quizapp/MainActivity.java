package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.QuizList;
import com.example.quizapp.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    QuizViewModel quizViewModel;
    List<Quiz> quizDataList;

    static int result = 0;
    static  int totalQuestions = 0;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        result = 0;
        totalQuestions = 0;
        DisplayFirstQuestion();
        activityMainBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNextQuestion();
            }
        });
    }
    public void DisplayFirstQuestion(){
        quizViewModel.getQuizListLiveData().observe(this, new Observer<QuizList>() {
            @Override
            public void onChanged(QuizList quizzes) {
                quizDataList = quizzes;
                activityMainBinding.quizQuestion.setText("Question 1" + quizzes.get(0).getQuestion());
                activityMainBinding.check1.setText(quizzes.get(0).getOption1());
                activityMainBinding.check2.setText(quizzes.get(0).getOption2());
                activityMainBinding.check3.setText(quizzes.get(0).getOption3());
                activityMainBinding.check4.setText(quizzes.get(0).getOption4());
            }
        });

    }
    private void DisplayNextQuestion() {
        if (activityMainBinding.btnNext.getText().equals("FINISH")){
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
        }
        int selectionOption = activityMainBinding.radioGroup.getCheckedRadioButtonId();

        if (selectionOption != -1){
            CheckBox checkBox = findViewById(selectionOption);

            if ((quizDataList.size()-i)>0){
                totalQuestions = quizDataList.size();
                if (checkBox.getText().toString().equals((quizDataList).get(i).getCorrectOption()));
                result ++;

                activityMainBinding.txtResult.setText("Correct Answer :" + result);

            }
            if (i == 0)
            {
                i++;
            }
            activityMainBinding.quizQuestion.setText("Question" +(i+1)+":" +quizDataList.get(i).getQuestion());
            activityMainBinding.check1.setText(quizDataList.get(i).getOption1());
            activityMainBinding.check2.setText(quizDataList.get(i).getOption2());
            activityMainBinding.check3.setText(quizDataList.get(i).getOption3());
            activityMainBinding.check4.setText(quizDataList.get(i).getOption4());

            if (i == (quizDataList.size()-1)){
                activityMainBinding.btnNext.setText("FINISH");

            }
            activityMainBinding.radioGroup.clearCheck();

            i++;


            }
        else {
            Toast.makeText(this, "Please Select The Option", Toast.LENGTH_SHORT).show();
        }
    }



}