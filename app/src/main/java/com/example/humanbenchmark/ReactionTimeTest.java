package com.example.humanbenchmark;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Random;

public class ReactionTimeTest extends AppCompatActivity {
    List<Integer> results;
    Integer roundCounter = -1;
    Integer roundNumber = 5;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time_test);
        Button redGreenButton = findViewById(R.id.redGreenButton);
        redGreenButton.setText("This is\n\"Reaction Time Test\"\nClick To start!");
        redGreenButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        roundCounter += 1;
                        if(roundCounter > roundNumber) {
                            showResults();
                        }
                        else {
                            playNextRound();
                        }
                    }

                    private void playNextRound() {
                        redGreenButton.setBackgroundResource(R.drawable.red_square);
                        if (timer != null) {

                            timer.cancel();
                            timer = null;
                        } else {
                            Random random = new Random();
                            int randomTime = random.nextInt(6000) + 3000;
                            startCountdown(randomTime);
                        }
                    }
                    private void showResults() {

                    }

                    void startCountdown(long millisInFuture) {
                        // Utwórz nowy obiekt CountDownTimer
                        timer = new CountDownTimer(millisInFuture, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                redGreenButton.setBackgroundResource(R.drawable.green_square);
                            }
                        };

                        // Uruchom odliczanie
                        timer.start();
                    }
                }
        );
    }
}