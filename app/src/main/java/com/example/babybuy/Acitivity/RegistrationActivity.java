package com.example.babybuy.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babybuy.Helperclass.DatabaseHelper;
import com.example.babybuy.R;
import com.example.babybuy.models.LoginModel;
import com.example.babybuy.models.UserModel;
import com.hbb20.CountryCodePicker;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    AppCompatButton registbtn;
    CountryCodePicker country;
    TextView loginlink;
    EditText email, fullname, password, username, confirmpass;
    RadioButton radioButton;
    RadioGroup radioGroup;
    private static int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registbtn = findViewById(R.id.registerbtn);
        email = findViewById(R.id.email);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.radioGrp);
        confirmpass = findViewById(R.id.confirmpassword);
        loginlink = findViewById(R.id.loginlink);
        country =findViewById(R.id.ccp);

        registbtn.setOnClickListener(v->{
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            String emailaddress = email.getText().toString();
            String name = fullname.getText().toString();
            String usernam = username.getText().toString();
            String pass = password.getText().toString();
            String gender = radioButton.getText().toString();
            String cpass = confirmpass.getText().toString();
            String countryvar = country.getSelectedCountryName();
            DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

             if(emailaddress.isEmpty() || name.isEmpty() || usernam.isEmpty() || pass.isEmpty() || gender.isEmpty() || countryvar.isEmpty()){
                 Toast.makeText(RegistrationActivity.this,"Empty Field is not accepted!", Toast.LENGTH_SHORT).show();
             }
             else{
                 if(pass.equals(cpass)){
                     List<String> login = databaseHelper.userDao().getallloginusernames();

                     if(login.contains(usernam)){
                         Toast.makeText(RegistrationActivity.this,"This username already Exists", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         databaseHelper.userDao().addUser(
                                 new UserModel(count,name,emailaddress,gender,countryvar)
                         );

                         databaseHelper.userDao().addlogincreds(
                                 new LoginModel(usernam,pass,count)
                         );
                         count++;
                         startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                         Toast.makeText(RegistrationActivity.this,"Successfully Registered", Toast.LENGTH_SHORT).show();
                     }


                 }
                 else{
                     Toast.makeText(RegistrationActivity.this,"Password Doesn't match!", Toast.LENGTH_SHORT).show();
                 }

             }


        });
        loginlink.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
    }
}