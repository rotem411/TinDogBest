package com.rotemarbiv.tin;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static User self;
    public EditText userName;
    public EditText password;
    public Button signInButton;
    public Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userNameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        signInButton = (Button) findViewById(R.id.signInButton);

        self = new User(userName.getText().toString(), password.getText().toString(), true);

        signInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // verify the user with server

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
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

}