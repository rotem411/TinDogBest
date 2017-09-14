package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rotemarbiv.tin.backend.BackendSimulator;
import com.rotemarbiv.tin.backend.Match;

/**
 * Created by laurescemama on 08/08/2017.
 */

public class ResultActivity extends AppCompatActivity {
    private static BackendSimulator backend = BackendSimulator.getInstance();

    public TextView fullName;
    public TextView userDate;
    public TextView userAddress;
    public TextView dogDate;
    public TextView dogAddress;
    public Button okButton;
    public ImageView profilePic;

    public Event userEvent;
    public Event dogEvent;

    public User selfUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_result);

        profilePic = (ImageView) findViewById(R.id.profilePic);
        fullName = (TextView)findViewById(R.id.fullNameLabel);
        userDate = (TextView)findViewById(R.id.userDateText);
        userAddress = (TextView)findViewById(R.id.userAddressText);
        dogDate = (TextView)findViewById(R.id.dogDateText);
        dogAddress = (TextView)findViewById(R.id.dogAddressText);


        okButton = (Button)findViewById(R.id.okButton);

        selfUser = (User) getIntent().getSerializableExtra("selfUser");
        userEvent = (Event) getIntent().getSerializableExtra("userEvent");
        dogEvent = (Event) getIntent().getSerializableExtra("dogEvent");

        //selfUser event is with your dog
        // dog event is with your selfUser
        //should be the other way around
        fullName.setText(dogEvent.walker.getFullName());
        userDate.setText(userEvent.getDateStr());
        userAddress.setText(userEvent.getAddress());
        dogDate.setText(dogEvent.getDateStr());
        dogAddress.setText(dogEvent.getAddress());
        com.rotemarbiv.tin.backend.User backendUser = backend.getUser(selfUser.getEmail());

        profilePic.setBackground(getResources().getDrawable(backendUser.getPersonPic()));

        fullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ProfileActivity.class);
                intent.putExtra("selfUser", selfUser);
                intent.putExtra("profileUser", dogEvent.walker);
                startActivity(intent);
            }
        });
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ProfileActivity.class);
                intent.putExtra("selfUser", selfUser);
                intent.putExtra("profileUser", dogEvent.walker);
                startActivity(intent);
            }
        });

    }

    public void okClicked(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("selfUser", selfUser);
        //update server
        startActivity(intent);
    }
}
