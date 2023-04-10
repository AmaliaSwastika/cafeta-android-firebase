package com.example.project11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void to_register(View dsp) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    public void to_mainActivity(View dsp) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}