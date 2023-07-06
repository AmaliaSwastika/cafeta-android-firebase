package com.example.project11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project11.R;
import com.example.project11.Userr;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText InputUsername, InputEmail, InputPassword;
    private Button btnRegister;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi EditText dan Button
        InputUsername = findViewById(R.id.InputUsername);
        InputEmail = findViewById(R.id.InputEmail);
        InputPassword = findViewById(R.id.InputPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("register");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nilai dari EditText
                String username = InputUsername.getText().toString().trim();
                String email = InputEmail.getText().toString().trim();
                String password = InputPassword.getText().toString().trim();

                // Membuat objek User
                Userr userr = new Userr(username, email, password);

                // Memasukkan data ke Firebase Database
                String userId = databaseReference.push().getKey();
                databaseReference.child(userId).setValue(userr);

                // Menampilkan pesan sukses atau melakukan tindakan lainnya
                // sesuai dengan kebutuhan Anda
                Toast.makeText(Register.this, "Register successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
 });
}
}