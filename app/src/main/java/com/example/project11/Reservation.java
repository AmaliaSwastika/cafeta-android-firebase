package com.example.project11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Reservation extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextDate;
    private Spinner spinnerOutlet, spinnerPerson, spinnerTime;
    private Button buttonSubmit, buttonPindah;

    private DatabaseReference databaseReference;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDate = findViewById(R.id.editTextDate);
        spinnerOutlet = findViewById(R.id.spinnerOutlet);
        spinnerPerson = findViewById(R.id.spinnerPerson);
        spinnerTime = findViewById(R.id.spinnerTime);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonPindah = findViewById(R.id.buttonPindah);


        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("reservation");

        // Initialize Spinner with static data
        ArrayAdapter<CharSequence> outletAdapter = ArrayAdapter.createFromResource(this, R.array.outlets, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> personAdapter = ArrayAdapter.createFromResource(this, R.array.person, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);

        spinnerOutlet.setAdapter(outletAdapter);
        spinnerPerson.setAdapter(personAdapter);
        spinnerTime.setAdapter(timeAdapter);

        // Set up DatePickerDialog
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(Reservation.this, dateSetListener, year, month, dayOfMonth);

        // Set onClick listener for the date EditText
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        buttonPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reservation.this, Reschedule.class);
                startActivity(intent);
            }
        });






        // Handle button submit click
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataToFirebase();
            }
        });
    }

    private void saveDataToFirebase() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String outlet = spinnerOutlet.getSelectedItem().toString();
        String person = spinnerPerson.getSelectedItem().toString();
        String time = spinnerTime.getSelectedItem().toString();
        String date = editTextDate.getText().toString().trim();

        // Melakukan validasi
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || date.isEmpty()) {
            Toast.makeText(Reservation.this, "Mohon isi semua field", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!phone.startsWith("0") || phone.length() < 10 || phone.length() > 12) {
            Toast.makeText(Reservation.this, "Phone number harus diawali dengan 0 dan memiliki panjang 10-12 digit", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User object
        User user = new User(name, email, phone, outlet, person,time,date);

        // Save data to Firebase
        String userId = databaseReference.push().getKey();
        databaseReference.child(userId).setValue(user);

        // Clear input fields
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPhone.setText("");
        editTextDate.setText("");

        // Display success message or perform other actions
        Toast.makeText(Reservation.this, "Reservasi telah berhasil", Toast.LENGTH_SHORT).show();

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            editTextDate.setText(dateFormatter.format(newDate.getTime()));
  }
};
}