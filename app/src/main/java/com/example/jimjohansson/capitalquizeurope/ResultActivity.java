package com.example.jimjohansson.capitalquizeurope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    private Button button;
    private TextView pointview;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        int points = intent.getIntExtra(GameActivity.POINTS_KEY, 0);

        pointview = findViewById(R.id.resultview);

        pointview.setText(String.valueOf(getString(R.string.result_headline)+ points+"/10!"));

        button();
    }

    public void button() {
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
}
