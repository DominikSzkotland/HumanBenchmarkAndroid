package com.example.humanbenchmark;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reactionTestButton;
        Button simonTestButton;
        Button numberSequenceButton;
        reactionTestButton = findViewById(R.id.reactionTimeButton);
        simonTestButton = findViewById(R.id.simonMemoryTest);
        numberSequenceButton = findViewById(R.id.numberSequenceTestButton);

        reactionTestButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ReactionTimeTest.class);
                        startActivity(intent);
                    }
                }
        );

        simonTestButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SimonMemoryTest.class);
                        startActivity(intent);
                    }
                }
        );

        numberSequenceButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, NumberSequenceTest.class);
                        startActivity(intent);
                    }
                }
        );
    }
}