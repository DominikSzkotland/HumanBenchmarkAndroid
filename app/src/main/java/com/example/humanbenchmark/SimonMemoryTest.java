package com.example.humanbenchmark;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonMemoryTest extends AppCompatActivity {
    boolean restartClick = false;
    List<Button> answers = new ArrayList<>();
    List<Button> simonQue = new ArrayList<>();
    Button[] buttons = new Button[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon_memory_test);
        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);

        TextView textView = findViewById(R.id.simonTitleTextView);
        textView.setText("Simon memory test\nClick any tile to start!");
        addNewButtonToQue(1);
        showQue();
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view instanceof Button) {
                    textView.setText("Simon memory test");
                    buttons[4].setText("");
                    if(restartClick)
                    {
                        restartClick = false;
                        addNewButtonToQue(1);
                        showQue();
                    }
                    else
                    {
                        Button clickedButton = (Button) view;
                        highlightButton(clickedButton, R.drawable.yellow_square,525);
                        checkWithQue(clickedButton);
                    }
                }
            }
        };

        for (Button button : buttons) {
            button.setOnClickListener(buttonClickListener);
        }
    }

    private void checkWithQue(Button clickedButton) {
        answers.add(clickedButton);
        if(answers.get(answers.size() - 1) == simonQue.get(answers.size() - 1))
        {
            highlightButton(clickedButton, R.drawable.green_square, 250);
            if(answers.size() == simonQue.size())
            {
                changeButtonsEnabledOption(buttons, false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttons[4].setText(String.valueOf(simonQue.size()));
                        changeColorOfAllButons(R.drawable.green_square);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttons[4].setText("");
                                changeColorOfAllButons(R.drawable.square_button);
                                addNewButtonToQue(1);
                                showQue();
                            }
                        }, 525);
                    }
                }, 525);


            }
        }
        else
        {
            changeButtonsEnabledOption(buttons, false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    buttons[4].setText(String.valueOf(simonQue.size() - 1));
                    changeColorOfAllButons(R.drawable.red_square);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = findViewById(R.id.simonTitleTextView);
                            changeColorOfAllButons(R.drawable.square_button);
                            textView.setText("Simon memory test\nClick any tile to try again!");
                            answers.clear();
                            simonQue.clear();
                            restartClick = true;
                            changeButtonsEnabledOption(buttons, true);
                        }
                    }, 1000);
                }
            }, 525);

        }
    }
    void changeColorOfAllButons(int source)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].setBackgroundResource(source);
        }
    }

    void addNewButtonToQue(int howMany)
    {
        for(int i = 0; i < howMany; i++)
        {
            Button buttonToHighlight = buttons[getRandomIndex(buttons.length - 1)];
            simonQue.add(buttonToHighlight);
        }
    }
    void showQue()
    {
        answers.clear();
        changeButtonsEnabledOption(buttons, false);
        Log.d("Iteration","counter: -1");
        showQue(0);
    }

    void showQue(int counter)
    {
        Log.d("Iteration","counter: "+counter+" simonQue.size(): "+simonQue.size());
        if (counter < simonQue.size())
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    highlightButton(simonQue.get(counter), R.drawable.yellow_square, 525);
                    showQue(counter + 1);
                }
            }, 620);
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeButtonsEnabledOption(buttons,true);
                }
            }, 750);
        }
    }

    void changeButtonsEnabledOption(Button[] buttonsToSet, boolean state)
    {
        for(int i = 0; i < buttonsToSet.length; i++)
        {
            buttonsToSet[i].setEnabled(state);
        }
    }

    private void highlightButton(Button button, int source, int time) {
        button.setBackgroundResource(source);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundResource(R.drawable.square_button);
            }
        }, time);

    }
    private int getRandomIndex(int max)
    {
        int result = new Random().nextInt(max);
        Log.d("RANDOM", String.valueOf(result));
        return result;
    }

}
