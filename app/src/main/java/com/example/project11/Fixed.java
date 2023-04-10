package com.example.project11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Fixed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed);
    }
    public void to_mainActivity(View dsp) {
        Intent intent = new Intent(Fixed.this, MainActivity.class);
        startActivity(intent);
    }
}