package com.example.project11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project11.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText InputUsername, InputPassword;
    private Button btnLogin;

    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi EditText, Button, dan TextView
        InputUsername = findViewById(R.id.InputUsername);
        InputPassword = findViewById(R.id.InputPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Inisialisasi Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("register");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nilai dari EditText
                String username = InputUsername.getText().toString().trim();
                String password = InputPassword.getText().toString().trim();

                // Mencari data pengguna berdasarkan email
                databaseReference.orderByChild("name").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Email ditemukan, verifikasi password
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                Userr userr = userSnapshot.getValue(Userr.class);
                                if (userr.getPassword().equals(password)) {
                                    // Password cocok, login berhasil
                                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                    // Lakukan tindakan selanjutnya setelah login berhasil
                                    // Contoh: Menampilkan data pengguna
                                    Toast.makeText(Login.this, "Email: " + userr.getEmail(), Toast.LENGTH_SHORT).show();
                                } else {
                                    // Password salah
                                    Toast.makeText(Login.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // Email tidak ditemukan
                            Toast.makeText(Login.this, "username not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Error dalam mengakses Firebase Database
                        Toast.makeText(Login.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


}
public void to_register(View dsp) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}