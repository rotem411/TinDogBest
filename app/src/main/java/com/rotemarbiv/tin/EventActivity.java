package com.rotemarbiv.tin;

import android.content.Intent;
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
    public TextView walkerName ;
    public TextView dogName;
    public MapView map;
    public EditText special;
    public Button walkDone;

    public Event event;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.event);

        event = (Event) getIntent().getSerializableExtra("eventClicked");

        System.out.println(event.getEventTitle()+"  "+event.isItMe);

        if (event.isItMe){
            walkDone = (Button)findViewById(R.id.walkDoneButton);
            walkDone.setVisibility(View.VISIBLE);
            walkDone.setClickable(true);
        }

        time = (TextView) findViewById(R.id.timeTitleLabel);
        date = (TextView) findViewById(R.id.dateTitleLabel);
        walkerName = (TextView) findViewById(R.id.walkerName);
        dogName = (TextView) findViewById(R.id.dogName);
//        map = (MapView) findViewById(R.id.mapView);
        special = (EditText) findViewById(R.id.commentsInput);


        time.setText(event.getTimeStr());
        date.setText(event.getDateStr());
        walkerName.setText(event.walker.fullName);
        dogName.setText((event.dog.dogName));
        special.setText(event.comments);

    }

        public void walkDoneClicked(View view){
            // send the server information that this event can be deleted
            Intent intent = new Intent(EventActivity.this, HomeActivity.class);
            if (event.isItMe){
                intent.putExtra("myEventToDelete", event);
            }
            startActivity(intent);
        }
}
