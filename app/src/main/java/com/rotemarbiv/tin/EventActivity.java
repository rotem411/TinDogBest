package com.rotemarbiv.tin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

/**
 * Created by dafnaarbiv on 25/07/2017.
 */

public class EventActivity extends AppCompatActivity {

    public TextView time;
    public TextView date;
    public EditText walkerName ;
    public EditText dogName;
    public MapView map;
    public EditText special;
    public Button walkDone;

    public Event event;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.event);

        if (event.isItMe){
            walkDone = (Button) findViewById(R.id.walkDoneButton);
            walkDone.setVisibility(View.VISIBLE);
            walkDone.setClickable(true);
        }

        time = (TextView) findViewById(R.id.timeTitleLabel);
        date = (TextView) findViewById(R.id.dateTitleLabel);
        walkerName = (EditText) findViewById(R.id.walkerName);
        dogName = (EditText) findViewById(R.id.dogNameInput);
//        map = (MapView) findViewById(R.id.mapView);
        special = (EditText) findViewById(R.id.commentsInput);



    }
}
