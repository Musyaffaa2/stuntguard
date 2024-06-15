package com.example.stuntguard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.DatePickerDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class InsertChildActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "InsertChildActivity";
    private String selectedDate;
    private ImageButton btnKeluar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText etName, etBirthDate, etHeight, etWeight, etHeadCircumference, etGender;
    private int age;

    private StorageReference storageReference;
    private Button btnSubmit;

    private Button btninputPhoto;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Uri imageUri;

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
        btninputPhoto = findViewById(R.id.buttonInputPhoto);
        etName = findViewById(R.id.editTextusernameChild);
        etBirthDate = findViewById(R.id.editTextglLahirchild);
        etHeight = findViewById(R.id.editTextheightchild);
        etWeight = findViewById(R.id.editTextWeightchild);
        etHeadCircumference = findViewById(R.id.editTextheadchild);
        etGender = findViewById(R.id.editTextgenderchild);
        btnSubmit = findViewById(R.id.buttonTambahAnak);
        btnSubmit.setOnClickListener(this);
        etBirthDate.setOnClickListener(this);
        btninputPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        // Initialize Firebase database reference
        String url = getResources().getString(R.string.urlDatabase);
        firebaseDatabase = FirebaseDatabase.getInstance(url);
        databaseReference = firebaseDatabase.getReference("children");
        storageReference = FirebaseStorage.getInstance().getReference();

        child = new Child();
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
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
        } else if (id == R.id.editTextglLahirchild) {
            showDatePickerDialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
        }
    }

    public void submitData() {
        Log.d(TAG, "submitData called");
        if (!validateForm()) {
            return;
        }
        if (imageUri != null) {
            uploadImage();
        } else {
            saveChildData(null);
        }
    }

    private void uploadImage() {
        // Mendapatkan referensi untuk menyimpan gambar dengan nama unik berdasarkan waktu saat ini
        StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

        // Mengunggah file ke Firebase Storage
        fileReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Jika pengunggahan berhasil, dapatkan URL download gambar
                        fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                // Simpan data anak ke Firebase Database bersama dengan URL gambar
                                saveChildData(imageUrl);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Jika pengunggahan gagal, tampilkan pesan kesalahan
                        Toast.makeText(InsertChildActivity.this, "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



//    private void saveChildData(String imageUrl) {
//        String name = etName.getText().toString();
//        String gender = etGender.getText().toString();
//        float beratBadan, tinggiBadan, lingkarKepala;
//        try {
//            beratBadan = Float.parseFloat(etWeight.getText().toString());
//            tinggiBadan = Float.parseFloat(etHeight.getText().toString());
//            lingkarKepala = Float.parseFloat(etHeadCircumference.getText().toString());
//        } catch (NumberFormatException e) {
//            Log.e(TAG, "Number format exception", e);
//            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Child newChild = new Child(name, selectedDate, gender, beratBadan, tinggiBadan, lingkarKepala, age);
//        newChild.setChildImageUrl(imageUrl); // Set URL gambar ke objek Child
//
//        // Check apakah user sudah login
//        if (currentUser != null) {
//            // Dapatkan UID user yang sedang login
//            String uid = currentUser.getUid();
//            // Simpan data anak baru ke Firebase Database di bawah node UID user
//            databaseReference.child(uid).push().setValue(newChild)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Log.d(TAG, "Child added successfully");
//                            Toast.makeText(InsertChildActivity.this, "Child added successfully", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d(TAG, "Failed to add child", e);
//                            Toast.makeText(InsertChildActivity.this, "Failed to add child", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } else {
//            Toast.makeText(InsertChildActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    private Date parseDate(String dateString) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            return dateFormat.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
private void saveChildData(String imageUrl) {
    String name = etName.getText().toString();
    String gender = etGender.getText().toString();
    float beratBadan, tinggiBadan, lingkarKepala;
    try {
        beratBadan = Float.parseFloat(etWeight.getText().toString());
        tinggiBadan = Float.parseFloat(etHeight.getText().toString());
        lingkarKepala = Float.parseFloat(etHeadCircumference.getText().toString());
    } catch (NumberFormatException e) {
        Log.e(TAG, "Number format exception", e);
        Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        return;
    }

    Child newChild = new Child(name, selectedDate, gender, beratBadan, tinggiBadan, lingkarKepala, age);
    newChild.setChildImageUrl(imageUrl); // Set URL gambar ke objek Child

    // Check apakah user sudah login
    if (currentUser != null) {
        // Dapatkan UID user yang sedang login
        String uid = currentUser.getUid();
        // Simpan data anak baru ke Firebase Database di bawah node UID user
        databaseReference.child(uid).push().setValue(newChild)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Child added successfully");
                        Toast.makeText(InsertChildActivity.this, "Child added successfully", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK); // Beri tahu bahwa data berhasil ditambahkan
                        finish();
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

    public void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(InsertChildActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat isoSdf = new SimpleDateFormat("yyyy-MM-dd");
                        selectedDate = isoSdf.format(selectedCalendar.getTime());
                        etBirthDate.setText(sdf.format(selectedCalendar.getTime()));

                        age = calculateAge(selectedCalendar.getTime());
                    }
                }, year, month, day);
        datePickerDialog.show();
    }


private int calculateAge(Date birthDate) {
    Calendar birthCalendar = Calendar.getInstance();
    birthCalendar.setTime(birthDate);

    Calendar today = Calendar.getInstance();

    int years = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
    int months = today.get(Calendar.MONTH) - birthCalendar.get(Calendar.MONTH);

    // Total bulan adalah jumlah tahun dikalikan 12 ditambah selisih bulan
    int totalMonths = years * 12 + months;

    // Jika hari dalam bulan saat ini lebih kecil dari hari dalam bulan kelahiran, kurangi satu bulan
    if (today.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
        totalMonths--;
    }

    return totalMonths;
}


}
