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
    public EditText Email;
    public EditText phoneNum;
    public User self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        fullName =  (TextView) findViewById(R.id.name);
        userName =  (EditText) findViewById(R.id.selfUserName);
        dogName =  (EditText) findViewById(R.id.selfDogName);
        dogSize =  (EditText) findViewById(R.id.selfDogSize);
        address =  (EditText) findViewById(R.id.selfAddress);
        Email =  (EditText) findViewById(R.id.selfEmail);
        phoneNum = (EditText) findViewById(R.id.selfTelephon);

        self = (User) getIntent().getSerializableExtra("Self");

        fullName.setText(self.fullName);
        userName.setText(self.userName);
        dogName.setText(self.dogName);
        dogSize.setText(self.dogSize);
        address.setText(self.address);
        Email.setText(self.mail);
        phoneNum.setText(self.phoneNumber);

    }



    public void goToHomePage(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}