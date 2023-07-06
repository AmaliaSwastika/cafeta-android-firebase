package com.example.project11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Outlets extends AppCompatActivity {
    ViewFlipper flipper;

    private Button reservasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlets);

        reservasi = findViewById(R.id.reservasi);
        reservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Outlets.this, Reservation.class);
                startActivity(intent);
         }
});

        ImageView bb = findViewById(R.id.bb);
        bb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(Outlets.this, HomeFragment.class);
                startActivity(intent);
            }
        });

        int imgarray[] = {R.drawable.jaguar};
        flipper = findViewById(R.id.flipper);

        for (int i = 0; i < imgarray.length; i++) {
            showimage(imgarray[i]);
        }

    }

    public void showimage(int img){
        ImageView imageview = new ImageView(this);
        imageview.setBackgroundResource(img);

        flipper.addView(imageview);
        flipper.setFlipInterval(0);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(this, android.R.anim.slide_out_right);
    }
}