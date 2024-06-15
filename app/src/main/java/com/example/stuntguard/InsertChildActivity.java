package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stuntguard.model.Child;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertChildActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "InsertChildActivity";

    private ImageButton btnKeluar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText etName, etBirthDate, etHeight, etWeight, etHeadCircumference, etGender;
    private Button btnSubmit;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_children);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Initialize UI components
        btnKeluar = findViewById(R.id.back_btn);
        btnKeluar.setOnClickListener(this);

        etName = findViewById(R.id.editTextusernameChild);
        etBirthDate = findViewById(R.id.editTextglLahirchild);
        etHeight = findViewById(R.id.editTextheightchild);
        etWeight = findViewById(R.id.editTextWeightchild);
        etHeadCircumference = findViewById(R.id.editTextheadchild);
        etGender = findViewById(R.id.editTextgenderchild);
        btnSubmit = findViewById(R.id.buttonTambahAnak);
        btnSubmit.setOnClickListener(this);

        // Initialize Firebase database reference
        String url = ;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("children");
        child = new Child();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_btn) {
            Log.d(TAG, "Back button clicked");
            finish();
        } else if (id == R.id.buttonTambahAnak) {
            Log.d(TAG, "Add Child button clicked");
            submitData();
        }
    }

    public void submitData() {
        Log.d(TAG, "submitData called");
        if (!validateForm()) {
            return;
        }
        String name = etName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        float height = Float.parseFloat(etHeight.getText().toString());
        float weight = Float.parseFloat(etWeight.getText().toString());
        float headCircumference = Float.parseFloat(etHeadCircumference.getText().toString());
        String gender = etGender.getText().toString();

        Child newChild = new Child(name, birthDate, height, weight, headCircumference, gender);

        // Check if the current user is not null
        if (currentUser != null) {
            // Get the UID of the current authenticated user
            String uid = currentUser.getUid();
            // Push the new child data to the database under the user's UID node
            databaseReference.child(uid).push().setValue(newChild)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "Child added successfully");
                            Toast.makeText(InsertChildActivity.this, "Child added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Failed to add child", e);
                            Toast.makeText(InsertChildActivity.this, "Failed to add child", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(InsertChildActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("Required");
            result = false;
        } else {
            etName.setError(null);
        }
        if (TextUtils.isEmpty(etBirthDate.getText().toString())) {
            etBirthDate.setError("Required");
            result = false;
        } else {
            etBirthDate.setError(null);
        }
        if (TextUtils.isEmpty(etHeight.getText().toString())) {
            etHeight.setError("Required");
            result = false;
        } else {
            etHeight.setError(null);
        }
        if (TextUtils.isEmpty(etWeight.getText().toString())) {
            etWeight.setError("Required");
            result = false;
        } else {
            etWeight.setError(null);
        }
        if (TextUtils.isEmpty(etHeadCircumference.getText().toString())) {
            etHeadCircumference.setError("Required");
            result = false;
        } else {
            etHeadCircumference.setError(null);
        }
        if (TextUtils.isEmpty(etGender.getText().toString())) {
            etGender.setError("Required");
            result = false;
        } else {
            etGender.setError(null);
        }
        return result;
    }
}
