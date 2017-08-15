package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by dafnaarbiv on 15/08/2017.
 */


//NOTES : Im doing it very stupid way , instead of using the same form and just choose if its a give or take,
    // Im making every form seperatly . may be at the end I will make it smarter



public class ChooseEventActivity extends AppCompatActivity {

    Button giveB;
    Button takeB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_event);

        giveB = (Button) findViewById(R.id.giveButton);
        takeB = (Button) findViewById(R.id.takeButton);

        giveB.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ChooseEventActivity.this, NewGiveEventActivity.class);
                startActivity(intent);
            }
        });

        takeB.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ChooseEventActivity.this, NewTakeEventActivity.class);
                startActivity(intent);
            }
        });


    }
}
