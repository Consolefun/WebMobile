package com.example.login_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Set variables for buttons and views
    private EditText usernameText;
    private EditText passwordText;
    private TextView errorText;
    private Button Login;
    private boolean validationCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        errorText = (TextView) findViewById(R.id.error);
        Login = (Button) findViewById(R.id.login);

        // Set up login to navigate from login to homepage
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate(usernameText.getText().toString(), passwordText.getText().toString());
            }
        });


    }

    private void Validate(String username, String password){
        //Verify if the username and password are not empty and setup a username and password
        if(!username.isEmpty() && !password.isEmpty()){
            if(username.equals("Chicken") && password.equals("Strip")){
                validationCheck = true;

            }

        }
        //check for validations from user inputs
        if(!validationCheck){
            errorText.setText("Must enter a valid username and password");
        }
        else{
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        }
    }
}
