package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rotemarbiv.tin.backend.BackendSimulator;

/**
 * Created by dafnaarbiv on 12/07/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    //    public Button homeButton;
    public TextView fullName;
    public EditText dogName;
    public Spinner dogSizeSpinner;
    public EditText address;
    public EditText email;
    public EditText phoneNum;
    public Button homeButton;
    public Button editButton;
    public User profileUser;
    public User selfUser;
    private static BackendSimulator backend = BackendSimulator.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        fullName =  (TextView) findViewById(R.id.profileName);
        dogName =  (EditText) findViewById(R.id.profileDogName);
        dogSizeSpinner =  (Spinner) findViewById(R.id.profileDogSize);
        address =  (EditText) findViewById(R.id.profileAddress);
        email =  (EditText) findViewById(R.id.profileEmail);
        phoneNum = (EditText) findViewById(R.id.profileTelephon);

        homeButton = (Button) findViewById((R.id.homeButton));
        editButton = (Button) findViewById((R.id.editButton));

        profileUser = (User) getIntent().getSerializableExtra("profileUser");
        selfUser = (User) getIntent().getSerializableExtra("selfUser"); //TODO: change better

        ArrayAdapter<CharSequence> dogSizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.dog_size_array, android.R.layout.simple_spinner_item);
        dogSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dogSizeSpinner.setAdapter(dogSizeAdapter);

        dogSizeSpinner.setEnabled(false);
        fullName.setText(profileUser.getFullName());
        dogName.setText(profileUser.getDogName());
        address.setText(profileUser.getAddress());
        email.setText(profileUser.getEmail());
        phoneNum.setText(profileUser.getPhoneNumber());

        if (profileUser.dogSize != null){
            dogSizeSpinner.setSelection(dogSizeAdapter.getPosition(profileUser.getDogSize()));
        }


        if (profileUser.getEmail() == selfUser.getEmail()){

            editButton.setVisibility(View.VISIBLE);
            editButton.setClickable(true);
        }

    }

    public void editClicked(View view){

        if (editButton.getText() == "Edit") {
            editButton.setText("Save");
            fullName.setEnabled(true);
            dogName.setEnabled(true);
            dogSizeSpinner.setEnabled(true);
            address.setEnabled(true);
            email.setEnabled(true);
            phoneNum.setEnabled(true);
        }
        else{
            //means it now says save
            fullName.setEnabled(false);
            dogName.setEnabled(false);
            dogSizeSpinner.setEnabled(false);
            address.setEnabled(false);
            email.setEnabled(false);
            phoneNum.setEnabled(false);

            dogSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    profileUser.dogSize = (String) parent.getItemAtPosition(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });

            String pastEmail = profileUser.getEmail();

            profileUser.setFullName(fullName.getText().toString());
            profileUser.setDogName(dogName.getText().toString());
            profileUser.setAddress(address.getText().toString());
            profileUser.setEmail(email.getText().toString());
            profileUser.setPhoneNumber(phoneNum.getText().toString());

            com.rotemarbiv.tin.backend.User backendUser = backend.getUser(pastEmail);
            backendUser.setName(profileUser.getFullName());
            backendUser.setDogName(profileUser.getDogName());
            backendUser.setAddress(profileUser.getEmail());
            backendUser.setPhoneNumber(profileUser.getPhoneNumber());
            backendUser.setDogSize(profileUser.getDogSize());
            // todo: how to update server
            editButton.setText("Edit");

        }
    }

    public void goToHomePage(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("selfUser", selfUser);
        startActivity(intent);
    }
}