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
import com.rotemarbiv.tin.backend.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static User self;
    private EditText email;
    private EditText password;
    private Button signInButton;
    private Button signUpButton;
    private String passwordString;
    private String emailString;
    private static BackendSimulator backend = BackendSimulator.getInstance();

    private com.rotemarbiv.tin.backend.User serverResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        signInButton = (Button) findViewById(R.id.signInButton);


        signInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                emailString = email.getText().toString();
                passwordString = password.getText().toString();

                if (email.getText().toString().trim().length()>0
                         && password.getText().toString().trim().length() > 0){
                            serverResponse = backend.signIn(emailString, passwordString);
                            if(serverResponse == null){
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "User Doesn't exist. Try again or sign up.", Toast.LENGTH_LONG);
                                toast.show();
                            }
                            else{
                                //sign in as the user - dont know who
                                self = User.convertBackendUserToUser(serverResponse);
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("selfUser", self);
                                startActivity(intent);
                            }
                 }

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