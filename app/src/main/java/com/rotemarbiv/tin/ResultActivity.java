package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rotemarbiv.tin.backend.Match;

/**
 * Created by laurescemama on 08/08/2017.
 */

public class ResultActivity extends AppCompatActivity {

    public TextView fullName;
    public Button okButton;
    public Event userEvent;
    public Event dogEvent;

    public User selfUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_result);

        fullName = (TextView)findViewById(R.id.fullNameLabel);
        okButton = (Button)findViewById(R.id.okButton);

        selfUser = (User) getIntent().getSerializableExtra("selfUser");
        userEvent = (Event)getIntent().getSerializableExtra("userEvent");
        dogEvent = (Event)getIntent().getSerializableExtra("dogEvent");

        if (userEvent.isItMe){
            fullName.setText(userEvent.dog.getDogName());
        }
        else{
            fullName.setText(userEvent.walker.getFullName());
        }

    }

    public void okClicked(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("selfUser", selfUser);
        //update server
        startActivity(intent);
    }
}
