package com.example.humanbenchmark;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class NumberSequenceTest extends AppCompatActivity {

    boolean isRestarted = false;
    private TextView numberDisplay;
    private EditText inputField;
    private Button checkButton;
    private Handler handler;
    private Runnable runnable;
    private int currentLength = 1;
    private int waitTime = 2000;
    private String currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_sequence_test);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        numberDisplay = findViewById(R.id.numberDisplay);
        inputField = findViewById(R.id.editTextNumberDecimal);
        checkButton = findViewById(R.id.checkButton);

        handler = new Handler();
        startNewRound();
    }

    private void startNewRound() {
        currentNumber = generateRandomNumber(currentLength);
        numberDisplay.setText(currentNumber);
        inputField.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);

        runnable = new Runnable() {
            @Override
            public void run() {
                numberDisplay.setText("Rewrite this number!");
                inputField.setVisibility(View.VISIBLE);
                checkButton.setVisibility(View.VISIBLE);
                inputField.setText("");
                waitTime += 425;
            }
        };

        handler.postDelayed(runnable, waitTime);
    }

    public void checkNumber(View view) {
        checkButton.setText("Check");
        if(isRestarted)
        {
            startNewRound();
            isRestarted = false;
        }
        else
        {
            String userInput = inputField.getText().toString();
            if (userInput.equals(currentNumber)) {
                currentLength++;
                startNewRound();
            } else {
                showResult();
            }
        }
    }

    private void showResult() {
        inputField.setText("");
        numberDisplay.setText("Game Over! Your score is: " + String.valueOf(currentLength - 1));
        checkButton.setText("Try again");
        currentLength = 1;
        waitTime = 2500;
        isRestarted = true;
    }

    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
}
