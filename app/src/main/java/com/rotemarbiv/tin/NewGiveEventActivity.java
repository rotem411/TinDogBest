package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextClock;

import java.util.Date;

/**
 * Created by dafnaarbiv on 15/08/2017.
 */

public class NewGiveEventActivity extends AppCompatActivity {
    Button submit;
    DatePicker datePicked;
    TextClock hourPicked;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_give);

        datePicked = (DatePicker) findViewById(R.id.datePicker);
        submit = (Button) findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

//                Date date = datePicked.; //TODO figure out how to do it
                //FUCKING DATE AND HOUR DO IT

                Intent intent = new Intent(NewGiveEventActivity.this, GiveResaultActivity.class);
                startActivity(intent);
            }
        });
    }
}
