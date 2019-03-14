package com.example.jimjohansson.capitalquizeurope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {


    private Button button;
    private TextView pointview;
    private EditText childEditText;
    private Button publishbutton;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        int points = intent.getIntExtra(GameActivity.POINTS_KEY, 0);

        pointview = findViewById(R.id.resultview);

        pointview.setText(String.valueOf(getString(R.string.result_headline)+ points+"/10!"));


        childEditText = (EditText) findViewById(R.id.edittext);



        PlayAgainButton();

        TextToFirebase();
    }

    public void PlayAgainButton() {
        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent myIntent = new Intent(ResultActivity.this,
                                GameActivity.class);
                        startActivity(myIntent);


                    }
                }

        );
    }

    public void TextToFirebase() {

        FirebaseDatabase dbase = FirebaseDatabase.getInstance();
        final DatabaseReference mref = dbase.getReference("User");

        publishbutton = (Button) findViewById(R.id.publish);

        publishbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String childvalue = childEditText.getText().toString();

                        mref.setValue(childvalue);

                        Toast.makeText(getApplicationContext(),"Added to Firebase Database", Toast.LENGTH_SHORT).show();


                    }

    }

        );


}

    }
