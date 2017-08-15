package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;

/**
 * Created by dafnaarbiv on 25/07/2017.
 */

public class EventActivity extends AppCompatActivity {

    public TextView userName ;
    public TextView dogName;
    public MapView map;
    public TextView special;
    public Button backButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        userName = (TextView) findViewById(R.id.walkerNameInput);
        dogName = (TextView) findViewById(R.id.dogNameInput);
        map = (MapView) findViewById(R.id.mapView);
        special = (TextView) findViewById(R.id.info);
        backButton = (Button) findViewById(R.id.backButton);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 1); //-1 is the default value

        Event curEvent = HomeActivity.events[position];
        userName.setText(curEvent.walker);
        dogName.setText(curEvent.dogName);

        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(EventActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });



    }
}
