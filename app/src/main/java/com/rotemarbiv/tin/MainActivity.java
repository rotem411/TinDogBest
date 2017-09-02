package com.rotemarbiv.tin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rotemarbiv.tin.backend.BackendSimulator;

public class MainActivity extends AppCompatActivity {

    public static User self;
    public EditText userName;
    public EditText password;
    public Button signInButton;
    public Button signUpButton;
    public String passwordString;
    private static BackendSimulator backend = BackendSimulator.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userNameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        passwordString = password.getText().toString();
        signInButton = (Button) findViewById(R.id.signInButton);
        self = new User(userName.getText().toString(), password.getText().toString(), true);

        signInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                if (MyApp.mGlobalUsers == null){
//                    Toast.makeText(getApplicationContext(), "There is no such User or the password is not correct ",Toast.LENGTH_LONG).show();
//                }
//                else if (MyApp.mGlobalUsers.get(userName.getText().toString()).equals(passwordString)){ // verify the user with server
//                    Toast.makeText(getApplicationContext(), "There is no such User or the password is not correct ",Toast.LENGTH_LONG).show();
//                }
//                else {
                    //sign in as the user - dont know who
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
//                }

            }
        });

        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // verify the user with server

                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settingsLabel:
                Toast.makeText(getApplicationContext(), "settings clicked ",Toast.LENGTH_LONG).show();

            case R.id.action_app:
                Toast.makeText(getApplicationContext(), "action clicked ",Toast.LENGTH_LONG).show();


        }
        return super.onOptionsItemSelected(item);
    }

}