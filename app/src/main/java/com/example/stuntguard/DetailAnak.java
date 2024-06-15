package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailAnak extends AppCompatActivity {

    private CircularImageView imageView;
    private TextView textViewName;
    private TextView textViewLastUpdated;
    private TextView textViewUsia;
    private TextView textViewBeratBadan;
    private TextView textViewTinggiBadan;
    private TextView textViewLingkarKepala;
    private String name;
    private String lastUpdated;
    private String imageUrl;
    private int usia;
    private double beratBadan;
    private double tinggiBadan;
    private double lingkarKepala;

    private static final int REQUEST_UPDATE_CHILD_DATA = 1; // Request code for startActivityForResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anak);

        imageView = findViewById(R.id.imageView);
        textViewName = findViewById(R.id.textViewName);
        textViewLastUpdated = findViewById(R.id.textViewLastUpdated);
        textViewUsia = findViewById(R.id.textViewUsia);
        textViewBeratBadan = findViewById(R.id.textViewBeratBadan);
        textViewTinggiBadan = findViewById(R.id.textViewTinggiBadan);
        textViewLingkarKepala = findViewById(R.id.textViewLingkarKepala);

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            lastUpdated = intent.getStringExtra("lastUpdated");
            imageUrl = intent.getStringExtra("imageUrl");
            usia = intent.getIntExtra("usia", -1);
            beratBadan = intent.getDoubleExtra("beratBadan", -1);
            tinggiBadan = intent.getDoubleExtra("tinggiBadan", -1);
            lingkarKepala = intent.getDoubleExtra("lingkarKepala", -1);

            updateUI();
        }

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        findViewById(R.id.buttonUpdate).setOnClickListener(view -> {
            Intent updateIntent = new Intent(DetailAnak.this, UpdateAnak.class);
            updateIntent.putExtra("name", name);
            updateIntent.putExtra("lastUpdated", lastUpdated);
            updateIntent.putExtra("imageUrl", imageUrl);
            updateIntent.putExtra("usia", usia);
            updateIntent.putExtra("beratBadan", beratBadan);
            updateIntent.putExtra("tinggiBadan", tinggiBadan);
            updateIntent.putExtra("lingkarKepala", lingkarKepala);
            startActivityForResult(updateIntent, REQUEST_UPDATE_CHILD_DATA);
        });
    }

    // Method to update UI with received data
    private void updateUI() {
        textViewName.setText(name);
        textViewLastUpdated.setText(lastUpdated);
        textViewUsia.setText(usia + " tahun");
        textViewBeratBadan.setText(beratBadan + " kg");
        textViewTinggiBadan.setText(tinggiBadan + " cm");
        textViewLingkarKepala.setText(lingkarKepala + " cm");

        // Load image using Glide with circular transformation
        Glide.with(this)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.mipmap.abe_round) // Placeholder image while loading
                .error(R.mipmap.abe_round) // Image to display if loading fails
                .into(imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_UPDATE_CHILD_DATA && resultCode == RESULT_OK && data != null) {
            // Update data from result intent
            lastUpdated = data.getStringExtra("lastUpdated");
            beratBadan = data.getDoubleExtra("beratBadan", -1);
            tinggiBadan = data.getDoubleExtra("tinggiBadan", -1);
            lingkarKepala = data.getDoubleExtra("lingkarKepala", -1);

            // Update UI with new data
            updateUI();

            // Show a toast or perform any other action as needed
            Toast.makeText(this, "Child data updated successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
