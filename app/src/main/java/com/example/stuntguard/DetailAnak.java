package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailAnak extends AppCompatActivity {

    private CircleImageView imageView;
    private TextView textViewName;
    private TextView textViewLastUpdated;
    private TextView textViewUsia;
    private TextView textViewBeratBadan;
    private TextView textViewTinggiBadan;
    private TextView textViewLingkarKepala;

    private Child child;

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
            // Extract Child object from intent
            child = new Child(
                    intent.getStringExtra("name"),
                    intent.getStringExtra("birthDate"),
                    intent.getStringExtra("gender"),
                    intent.getFloatExtra("beratBadan", 0),
                    intent.getFloatExtra("tinggiBadan", 0),
                    intent.getFloatExtra("lingkarKepala", 0),
                    intent.getIntExtra("age", 0)
            );

            updateUI();
        }

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        findViewById(R.id.buttonUpdate).setOnClickListener(view -> {
            // Start UpdateAnak activity with Child object
            Intent updateIntent = new Intent(DetailAnak.this, UpdateAnak.class);
            updateIntent.putExtra("child", (CharSequence) child);
            startActivityForResult(updateIntent, REQUEST_UPDATE_CHILD_DATA);
        });
    }



    // Method to update UI with Child data
    private void updateUI() {
        textViewName.setText(child.getName());
        textViewLastUpdated.setText(child.getLastUpdated());
        textViewUsia.setText(child.getAge() + " tahun");
        textViewBeratBadan.setText(child.getBeratBadan() + " kg");
        textViewTinggiBadan.setText(child.getTinggiBadan() + " cm");
        textViewLingkarKepala.setText(child.getLingkarKepala() + " cm");

        // Load image using Glide with circular transformation
        Glide.with(this)
                .load(child.getChildImageUrl())
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
            child.setLastUpdated(data.getStringExtra("lastUpdated"));
            child.setBeratBadan(data.getFloatExtra("beratBadan", 0));
            child.setTinggiBadan(data.getFloatExtra("tinggiBadan", 0));
            child.setLingkarKepala(data.getFloatExtra("lingkarKepala", 0));

            // Update UI with new data
            updateUI();

            // Show a toast or perform any other action as needed
            Toast.makeText(this, "Child data updated successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

//package com.example.stuntguard;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//
//public class DetailAnak extends AppCompatActivity {
//
//    private CircularImageView imageView;
//    private TextView textViewName;
//    private TextView textViewLastUpdated;
//    private TextView textViewUsia;
//    private TextView textViewBeratBadan;
//    private TextView textViewTinggiBadan;
//    private TextView textViewLingkarKepala;
//    private String name;
//    private String lastUpdated;
//    private String imageUrl;
//    private int usia;
//    private double beratBadan;
//    private double tinggiBadan;
//    private double lingkarKepala;
//
//    private static final int REQUEST_UPDATE_CHILD_DATA = 1; // Request code for startActivityForResult
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_anak);
//
//        imageView = findViewById(R.id.imageView);
//        textViewName = findViewById(R.id.textViewName);
//        textViewLastUpdated = findViewById(R.id.textViewLastUpdated);
//        textViewUsia = findViewById(R.id.textViewUsia);
//        textViewBeratBadan = findViewById(R.id.textViewBeratBadan);
//        textViewTinggiBadan = findViewById(R.id.textViewTinggiBadan);
//        textViewLingkarKepala = findViewById(R.id.textViewLingkarKepala);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            name = intent.getStringExtra("name");
//            lastUpdated = intent.getStringExtra("lastUpdated");
//            imageUrl = intent.getStringExtra("imageUrl");
//            usia = intent.getIntExtra("usia", -1);
//            beratBadan = intent.getDoubleExtra("beratBadan", -1);
//            tinggiBadan = intent.getDoubleExtra("tinggiBadan", -1);
//            lingkarKepala = intent.getDoubleExtra("lingkarKepala", -1);
//
//            updateUI();
//        }
//
//        findViewById(R.id.back_button).setOnClickListener(view -> finish());
//
//        findViewById(R.id.buttonUpdate).setOnClickListener(view -> {
//            Intent updateIntent = new Intent(DetailAnak.this, UpdateAnak.class);
//            updateIntent.putExtra("name", name);
//            updateIntent.putExtra("lastUpdated", lastUpdated);
//            updateIntent.putExtra("imageUrl", imageUrl);
//            updateIntent.putExtra("usia", usia);
//            updateIntent.putExtra("beratBadan", beratBadan);
//            updateIntent.putExtra("tinggiBadan", tinggiBadan);
//            updateIntent.putExtra("lingkarKepala", lingkarKepala);
//            startActivityForResult(updateIntent, REQUEST_UPDATE_CHILD_DATA);
//        });
//    }
//
//    // Method to update UI with received data
//    private void updateUI() {
//        textViewName.setText(name);
//        textViewLastUpdated.setText(lastUpdated);
//        textViewUsia.setText(usia + " tahun");
//        textViewBeratBadan.setText(beratBadan + " kg");
//        textViewTinggiBadan.setText(tinggiBadan + " cm");
//        textViewLingkarKepala.setText(lingkarKepala + " cm");
//
//        // Load image using Glide with circular transformation
//        Glide.with(this)
//                .load(imageUrl)
//                .apply(RequestOptions.circleCropTransform())
//                .placeholder(R.mipmap.abe_round) // Placeholder image while loading
//                .error(R.mipmap.abe_round) // Image to display if loading fails
//                .into(imageView);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_UPDATE_CHILD_DATA && resultCode == RESULT_OK && data != null) {
//            // Update data from result intent
//            lastUpdated = data.getStringExtra("lastUpdated");
//            beratBadan = data.getDoubleExtra("beratBadan", -1);
//            tinggiBadan = data.getDoubleExtra("tinggiBadan", -1);
//            lingkarKepala = data.getDoubleExtra("lingkarKepala", -1);
//
//            // Update UI with new data
//            updateUI();
//
//            // Show a toast or perform any other action as needed
//            Toast.makeText(this, "Child data updated successfully", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
