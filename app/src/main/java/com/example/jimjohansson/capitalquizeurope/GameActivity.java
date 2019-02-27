package com.example.jimjohansson.capitalquizeurope;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView questionView;
    private Button b1;
    private Button b2;
    private Button b3;


    List<String> answersOnButtons = new ArrayList<>();
    //List<String> countries  = new ArrayList(Arrays.asList(getString(R.string.france), getString(R.string.norway), getString(R.string.sweden), getString(R.string.russia), getString(R.string.spain), getString(R.string.germany)));
    private List<String> answers;
    private List<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionView = findViewById(R.id.country);
        countries = new ArrayList(Arrays.asList(getString(R.string.france), getString(R.string.norway), getString(R.string.sweden), getString(R.string.russia), getString(R.string.spain), getString(R.string.germany)));

        answers = new ArrayList(Arrays.asList(getString(R.string.paris), getString(R.string.oslo), getString(R.string.stockholm), getString(R.string.moscow), getString(R.string.madrid), getString(R.string.berlin)));


        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);

        fillScreen();
    }

    private void fillScreen() {

        Random randomGenerator = new Random();

        final int rightAnswerIndex = randomGenerator.nextInt(countries.size());

        questionView.setText(countries.get(rightAnswerIndex));

        ArrayList<Button> buttons = new ArrayList<>();

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        int rightButtonIndex = randomGenerator.nextInt(buttons.size());

        final Button rightButton = buttons.get(rightButtonIndex);
        buttons.remove(rightButton);
        rightButton.setText(answers.get(rightAnswerIndex));
        answersOnButtons.add(answers.get(rightAnswerIndex));




        //hade b1 innan
            b1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            fillScreen();


                        }
                    }
            );


        }



    }
