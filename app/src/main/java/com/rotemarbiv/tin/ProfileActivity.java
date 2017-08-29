package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dafnaarbiv on 12/07/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    //    public Button homeButton;
    public TextView fullName;
    public EditText userName;
    public EditText dogName;
    public EditText dogSize;
    public EditText address;
    public EditText email;
    public EditText phoneNum;
    public Button homeButton;
    public Button editButton;
    public User user;
    public User selfUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        fullName =  (TextView) findViewById(R.id.profileName);
        userName =  (EditText) findViewById(R.id.profileUserName);
        dogName =  (EditText) findViewById(R.id.profileDogName);
        dogSize =  (EditText) findViewById(R.id.profileDogSize);
        address =  (EditText) findViewById(R.id.profileAddress);
        email =  (EditText) findViewById(R.id.profileEmail);
        phoneNum = (EditText) findViewById(R.id.profileTelephon);

        homeButton = (Button) findViewById((R.id.homeButton));
        editButton = (Button) findViewById((R.id.editButton));

        user = (User) getIntent().getSerializableExtra("profileUser");
        selfUser = user; //TODO: change better

        fullName.setText(user.fullName);
        userName.setText(user.userName);
        dogName.setText(user.dogName);
        dogSize.setText(user.dogSize);
        address.setText(user.address);
        email.setText(user.email);
        phoneNum.setText(user.phoneNumber);

        if (user.userName == selfUser.userName){

            editButton.setVisibility(View.VISIBLE);
            editButton.setClickable(true);
        }

    }

    public void editClicked(View view){

        if (editButton.getText() == "Edit") {
            editButton.setText("Save");
            fullName.setEnabled(true);
            userName.setEnabled(true);
            dogName.setEnabled(true);
            dogSize.setEnabled(true);
            address.setEnabled(true);
            email.setEnabled(true);
            phoneNum.setEnabled(true);
        }
        else{
            //means it now says save
            fullName.setEnabled(false);
            userName.setEnabled(false);
            dogName.setEnabled(false);
            dogSize.setEnabled(false);
            address.setEnabled(false);
            email.setEnabled(false);
            phoneNum.setEnabled(false);

            user.fullName = fullName.getText().toString();
            user.userName = userName.getText().toString();
            user.dogName = dogName.getText().toString();
            user.dogSize = dogSize.getText().toString();
            user.address = address.getText().toString();
            user.email = email.getText().toString();
            user.phoneNumber = phoneNum.getText().toString();

            editButton.setText("Edit");

        }
    }

    public void goToHomePage(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}