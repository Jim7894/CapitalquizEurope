package com.example.jimjohansson.capitalquizeurope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static String POINTS_KEY = "play_points";
    private TextView questionView;
    private Button b1;
    private Button b2;
    private Button b3;
    private TextView pointsview;

    private int points = 0;
    List<String> answersOnButtons = new ArrayList<>();
    //List<String> countries  = new ArrayList(Arrays.asList(getString(R.string.france), getString(R.string.norway), getString(R.string.sweden), getString(R.string.russia), getString(R.string.spain), getString(R.string.germany)));
    private List<String> answers;
    private List<String> countries;
    private FirebaseDatabase mdatabase;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        //Firebase.setAndroidContext(this);

        pointsview = findViewById(R.id.pointsview);

        questionView = findViewById(R.id.country);

        countries = //new ArrayList(Arrays.asList(getString(R.string.france), getString(R.string.norway), getString(R.string.sweden), getString(R.string.russia), getString(R.string.spain), getString(R.string.germany), getString(R.string.finland), getString(R.string.great_britain), getString(R.string.czech_republic), getString(R.string.greece), getString(R.string.italy), getString(R.string.hungary)));

        answers = new ArrayList(Arrays.asList(getString(R.string.paris), getString(R.string.oslo), getString(R.string.stockholm), getString(R.string.moscow), getString(R.string.madrid), getString(R.string.berlin), getString(R.string.helsinki), getString(R.string.london), getString(R.string.prag), getString(R.string.athens), getString(R.string.rome), getString(R.string.budapest)));

        
        DatabaseReference countriesRef = FirebaseDatabase.getInstance().getReference("countries");


       
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);




        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);


            }

            private void showData(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    GameActivity GA = new GameActivity();


                   

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });*/

        fillScreen();
    }

    private void fillScreen() {

        if (2 < answers.size()) {
            pointsview.setText(String.valueOf(points));

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

        for (Button button : buttons) {
            int wrongAnswerIndex;
            // loop till we get wrong answer
            do {
                wrongAnswerIndex = randomGenerator.nextInt(answers.size());
            } while (answersOnButtons.contains(answers.get(wrongAnswerIndex)));

            button.setText(answers.get(wrongAnswerIndex));
            answersOnButtons.add(answers.get(wrongAnswerIndex));


            button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            fillScreen();

                        }
                    }


            );

        }

        rightButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            points++;
                            fillScreen();


                        }
                    }
            );

        countries.remove(rightAnswerIndex);
        answers.remove(rightAnswerIndex);
        answersOnButtons.clear();

        }

       else {

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(POINTS_KEY, points);
            startActivity(intent);
            finish();
        }



    }

}



