package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    androidx.gridlayout.widget.GridLayout gridLayout;
    TextView timerTextView;
    TextView questionTextView;
    TextView scoreTextView;
    TextView finishedTextView;
    Button playAgainButton;
    int timer = 30;
    int questionNumber = 0;
    int score = 0;
    int num1;
    int num2;
    int answer;
    int optionValue;
    int tag;
    CountDownTimer countDownTimer;
    Random random = new Random();

    public void setScore(){
        num1 = random.nextInt(50);
        num2 = random.nextInt(50);
        answer = num1 + num2;

        questionTextView.setText(num1 + " + " + num2);

        tag = random.nextInt(4);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            Button button = (Button) child;
            if(i == tag){
                optionValue = answer;
            }
            else{
                optionValue = random.nextInt(100);
            }
            button.setText(Integer.toString(optionValue));
        }

        scoreTextView.setText(score + "/" + questionNumber);
    }

    public void setTimer(){
        countDownTimer = new CountDownTimer(timer * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int) millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                finishedTextView.setText("Time's up!\nYour Score is " + score + "/" + questionNumber);
                finishedTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    View child = gridLayout.getChildAt(i);
                    Button button = (Button) child;
                    button.setEnabled(false);
                }
            }
        }.start();
    }

    public void selectOption(View view){
        Button option = (Button) view;
        if(Integer.parseInt(option.getTag().toString()) == tag){
            score++;
        }
        questionNumber++;
        setScore();
    }

    public void playAgain(View view){
        score = 0;
        questionNumber = 0;

        setScore();
        setTimer();
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            Button button = (Button) child;
            button.setEnabled(true);
        }
        finishedTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        finishedTextView = findViewById(R.id.finishedTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        setScore();
        setTimer();
    }
}
