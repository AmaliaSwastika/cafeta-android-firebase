package com.example.project11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Menus extends AppCompatActivity {
    ViewFlipper flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        Button myButton = findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menus.this, Cart.class);
                startActivity(intent);
            }
        });

        ImageView bb2 = findViewById(R.id.bb2);
        bb2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Menus.this, Cart.class);
                startActivity(intent);
            }
        });

        ImageView bb = findViewById(R.id.bb);
        bb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Menus.this, HomeFragment.class);
                startActivity(intent);
            }
        });

        int imgarray[] = {R.drawable.sateyam, R.drawable.sateyam2, R.drawable.sateyam3};
        flipper = findViewById(R.id.flipper);

        for (int i = 0; i < imgarray.length; i++) {
            showimage(imgarray[i]);
        }

    }
    public void showimage(int img){
        ImageView imageview = new ImageView(this);
        imageview.setBackgroundResource(img);

        flipper.addView(imageview);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(this, android.R.anim.slide_out_right);
    }
}