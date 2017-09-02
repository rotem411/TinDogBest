package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by laurescemama on 08/08/2017.
 */

public class ResultActivity extends AppCompatActivity {

    public TextView fullName;
    public Button okButton;
    public Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_result);

        fullName = (TextView)findViewById(R.id.fullNameLabel);
        okButton = (Button)findViewById(R.id.okButton);
        event = (Event) getIntent().getSerializableExtra("newEventFound");

        if (event.isItMe){
            fullName.setText(event.dog.getDogName());
        }
        else{
            fullName.setText(event.walker.getFullName());
        }

    }

    public void okClicked(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("newEventFound", event);
        //update server
        startActivity(intent);
    }
}
