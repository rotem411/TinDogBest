package com.rotemarbiv.tin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.EditText;

import com.google.android.gms.maps.MapView;

/**
 * Created by dafnaarbiv on 25/07/2017.
 */

public class EventActivity extends AppCompatActivity {

    public EditText userName ;
    public EditText dogName;
    public MapView map;
    public EditText special;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.event);

//        userName = (EditText) findViewById(R.id.walkerNameInput);
//        dogName = (EditText) findViewById(R.id.dogNameInput);
//        map = (MapView) findViewById(R.id.mapView);
//        special = (EditText) findViewById(R.id.info);



    }
}
