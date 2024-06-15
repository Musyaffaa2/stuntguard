package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateAnak extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextHead;
    private int id;
    private String name;
    private double beratBadan;
    private double tinggiBadan;
    private double lingkarKepala;
    private int usia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_anak);

        // Initialize views
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHead = findViewById(R.id.editTextHead);
        Toolbar toolbar = findViewById(R.id.topbar);
        setSupportActionBar(toolbar);

        // Hide default title from action bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize toolbar title TextView
        TextView toolbarTitle = findViewById(R.id.toolbar_title);

        // Receive data from DetailAnak
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            name = intent.getStringExtra("name");
            usia = intent.getIntExtra("usia", -1);
            beratBadan = intent.getDoubleExtra("beratBadan", -1);
            tinggiBadan = intent.getDoubleExtra("tinggiBadan", -1);
            lingkarKepala = intent.getDoubleExtra("lingkarKepala", -1);

            // Set initial values to EditTexts
            editTextHeight.setText(String.valueOf(tinggiBadan));
            editTextWeight.setText(String.valueOf(beratBadan));
            editTextHead.setText(String.valueOf(lingkarKepala));

            // Update toolbar title
            if (name != null && toolbarTitle != null) {
                toolbarTitle.setText(name);
            }
        }

        // Set back button functionality
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity and go back
            }
        });

        // Set button update functionality
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateChildData();
            }
        });
    }

    private void updateChildData() {
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();
        String headCircumferenceStr = editTextHead.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty() || headCircumferenceStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double updatedHeight = Double.parseDouble(heightStr);
            double updatedWeight = Double.parseDouble(weightStr);
            double updatedHeadCircumference = Double.parseDouble(headCircumferenceStr);

            // Get current timestamp
            String lastUpdated = getCurrentTimeStamp();

            // Send back updated data to DetailAnak
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", id);
            resultIntent.putExtra("name", name);
            resultIntent.putExtra("usia", usia);
            resultIntent.putExtra("beratBadan", updatedWeight);
            resultIntent.putExtra("tinggiBadan", updatedHeight);
            resultIntent.putExtra("lingkarKepala", updatedHeadCircumference);
            resultIntent.putExtra("lastUpdated", lastUpdated);
            setResult(RESULT_OK, resultIntent);
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to get current timestamp
    private String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
