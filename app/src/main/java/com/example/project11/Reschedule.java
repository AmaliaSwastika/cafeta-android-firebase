package com.example.project11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Reschedule extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextDate;
    private Spinner spinnerOutlet, spinnerPerson, spinnerTime;
    private Button buttonSearch, buttonUpdate, buttonDelete;

    DatabaseReference databaseReference;
    DataSnapshot dataSnapshot; // Declare dataSnapshot as a class member variable

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reschedule);

        editTextName = findViewById(R.  id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDate = findViewById(R.id.editTextDate);
        spinnerOutlet = findViewById(R.id.spinnerOutlet);
        spinnerPerson = findViewById(R.id.spinnerPerson);
        spinnerTime = findViewById(R.id.spinnerTime);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("reservation");

        // Inisialisasi Spinner dengan data statis
        ArrayAdapter<CharSequence> outletAdapter = ArrayAdapter.createFromResource(this, R.array.outlets, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> personAdapter = ArrayAdapter.createFromResource(this, R.array.person, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);

        spinnerOutlet.setAdapter(outletAdapter);
        spinnerPerson.setAdapter(personAdapter);
        spinnerTime.setAdapter(timeAdapter);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchUser();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(Reschedule.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void searchUser() {
        String email = editTextEmail.getText().toString().trim();

        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Reschedule.this.dataSnapshot = dataSnapshot; // Assign the dataSnapshot to the class member variable
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        displayUser(user);
                        enableUpdateAndDeleteButtons();
                    }
                } else {
                    clearForm();
                    disableUpdateAndDeleteButtons();
                    Toast.makeText(Reschedule.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(Reschedule.this, "Failed to search user: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void displayUser(User user) {
        editTextName.setText(user.getName());
        editTextEmail.setText(user.getEmail());
        editTextPhone.setText(user.getPhone());
        editTextDate.setText(user.getDate());
        spinnerOutlet.setSelection(getSpinnerIndex(spinnerOutlet, user.getOutlet()));
        spinnerPerson.setSelection(getSpinnerIndex(spinnerPerson, user.getPerson()));
        spinnerTime.setSelection(getSpinnerIndex(spinnerTime, user.getTime()));
    }

    private int getSpinnerIndex(Spinner spinner, String value) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
        return adapter.getPosition(value);
    }

    private void enableUpdateAndDeleteButtons() {
        buttonUpdate.setEnabled(true);
        buttonDelete.setEnabled(true);
    }

    private void disableUpdateAndDeleteButtons() {
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);
    }

    private void updateUser() {
        String email = editTextEmail.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String outlet = spinnerOutlet.getSelectedItem().toString();
        String person = spinnerPerson.getSelectedItem().toString();
        String time = spinnerTime.getSelectedItem().toString();
        String date = editTextDate.getText().toString().trim();

        // Create User object
        User user = new User(name, email, phone, outlet, person, time, date);

        // Melakukan validasi
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || date.isEmpty()) {
            Toast.makeText(Reschedule.this, "Mohon isi semua field", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!phone.startsWith("0") || phone.length() < 10 || phone.length() > 12) {
            Toast.makeText(Reschedule.this, "Phone number harus diawali dengan 0 dan memiliki panjang 10-12 digit", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = getUserId(email);
        if (userId != null) {
            DatabaseReference userRef = databaseReference.child(userId);
            userRef.child("name").setValue(user.getName());
            userRef.child("phone").setValue(user.getPhone());
            userRef.child("outlet").setValue(user.getOutlet());
            userRef.child("person").setValue(user.getPerson());
            userRef.child("time").setValue(user.getTime());
            userRef.child("date").setValue(user.getDate());

            Toast.makeText(Reschedule.this, "User updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Reschedule.this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUser() {
        String email = editTextEmail.getText().toString().trim();

        String userId = getUserId(email);
        if (userId != null) {
            databaseReference.child(userId).removeValue();
            clearForm();
            disableUpdateAndDeleteButtons();
            Toast.makeText(Reschedule.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Reschedule.this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }


    private void clearForm() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPhone.setText("");
        spinnerOutlet.setSelection(0);
        spinnerPerson.setSelection(0);
        spinnerTime.setSelection(0);
        editTextDate.setText("");
    }

    private String getUserId(String email) {
        String userId = null;

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            User user = snapshot.getValue(User.class);
            if (user.getEmail().equals(email)) {
                userId = snapshot.getKey();
                break;
            }
        }

        return userId;
}
}