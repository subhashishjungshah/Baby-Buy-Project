package com.example.babybuy.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.Helperclass.SessionManagement;
import com.example.babybuy.R;
import com.example.babybuy.models.LoginModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton logintbn;
    EditText email, password;
    TextView registerbtn;
    @Override
    protected void onStart() {
        super.onStart();
        // checking if user is logged in or not
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userID = sessionManagement.getSession();

        // WHen there exists session, move to main activity
        if(userID!=-1){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else{
            //do nothing
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logintbn = findViewById(R.id.appCompatButton);
        registerbtn = findViewById(R.id.registerlink);
        email = findViewById(R.id.emailtextbox);
        password = findViewById(R.id.passwordtextbox);

        //Instance of our database class
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        // Login Functionality
        logintbn.setOnClickListener(v->{
            String emaill = email.getText().toString();
            String pass = password.getText().toString();

            // Executing query to check user credientials
            List<LoginModel> checkusernameandpassword = databaseHelper.userDao().checkusernameandpassword(emaill, pass);

            //Validating user input
            if(emaill.isEmpty()||pass.isEmpty()){
                Toast.makeText(LoginActivity.this, "Username or Password is Empty!", Toast.LENGTH_SHORT).show();
            }
            else{
                // Validating user's credentials
                if(checkusernameandpassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Username and Password Incorrect!", Toast.LENGTH_SHORT).show();
                }

                // Logic for when username and password is correct
                else{

                    // Storing session for login user
                    LoginModel user = checkusernameandpassword.get(0);
                    SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                    sessionManagement.SaveSession(user);

                    // Navigating towards main activity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();

                }

            }
        });
        // Navigating to Registration Page
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                Toast.makeText(LoginActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });


    }


}