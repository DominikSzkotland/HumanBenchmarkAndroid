package com.example.humanbenchmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class ReactionTimeTest extends AppCompatActivity {

    private Button redGreenButton;
    private long startTime = 0;
    private long stopTime = 0;
    private boolean questionRunning = false;
    private boolean answerRunning = false;
    private Handler handler = new Handler();
    private Runnable waitingForAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time_test);

        redGreenButton = findViewById(R.id.redGreenButton);
        redGreenButton.setText("This is\n\"Reaction Time Test\"\nClick To start!");

        redGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionRunning || answerRunning) {
                    if (questionRunning) {
                        redGreenButton.setText("Too fast!\nClick to try again!");
                        handler.removeCallbacks(waitingForAnswer);
                        questionRunning = false;
                    } else if (answerRunning) {
                        stopTime = System.currentTimeMillis();
                        long reactionTime = stopTime - startTime;
                        redGreenButton.setText("Time: " + reactionTime + "ms\nClick to try again!");
                        answerRunning = false;
                    }
                } else {
                    startQuestion();
                }
            }
        });
    }

    private int getNewWaitTime() {
        return new Random().nextInt(4500) + 3000;
    }

    private void startQuestion() {
        questionRunning = true;
        redGreenButton.setBackgroundResource(R.drawable.red_square);
        redGreenButton.setText("Wait!");

        int waitTime = getNewWaitTime();
        waitingForAnswer = new Runnable() {
            @Override
            public void run() {
                if (questionRunning) {
                    startAnswer();
                }
            }
        };
        handler.postDelayed(waitingForAnswer, waitTime);
    }

    private void startAnswer() {
        questionRunning = false;
        answerRunning = true;
        redGreenButton.setBackgroundResource(R.drawable.green_square);
        redGreenButton.setText("Click now!");
        startTime = System.currentTimeMillis();
    }
}
