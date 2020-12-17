package com.m1mpdam.yourtech.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.m1mpdam.yourtech.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPwd;
    Button btnLogin;
    boolean isEmailValid, isPasswordValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtPwd = findViewById(R.id.edt_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String edt_email = edtEmail.getText().toString();
        String edt_pwd = edtPwd.getText().toString();

        // Check for a valid Email
        if (edt_email.isEmpty()) {
            edtEmail.setError("Please enter your Email.");
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email).matches()) {
            edtEmail.setError("enter a valid Email");
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        // Check for a valid Password
        if (edt_pwd.isEmpty()) {
            edtPwd.setError("Please enter your Password.");
            isPasswordValid = false;
        } else if (edt_pwd.length() < 6) {
            edtPwd.setError("At Least 6 caracters.");
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        // If Email and Password are Valid.
        if (isEmailValid && isPasswordValid) {
            successLogin();
        }
    }

    private void successLogin() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(Constants.PREF_IS_CONNECTED, true);
        editor.apply();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}