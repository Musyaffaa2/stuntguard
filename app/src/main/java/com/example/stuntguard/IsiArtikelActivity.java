package com.example.stuntguard;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IsiArtikelActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_artikel);

        Toolbar toolbar = findViewById(R.id.topbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });

        // Get the key from Intent
        String key = getIntent().getStringExtra("key");

        // Initialize Firebase Database reference
        firebaseDatabase = FirebaseDatabase.getInstance("https://stuntguard-1cd62-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();

        // Find views
        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.title);
        TextView textViewContent = findViewById(R.id.content);

        // Query Firebase database to get the article with the provided key
        databaseReference.child("artikels").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the Artikel object
                Artikel artikel = snapshot.getValue(Artikel.class);

                if (artikel != null) {
                    // Load the image using Glide
                    Glide.with(IsiArtikelActivity.this)
                            .load(artikel.getImageUrl())
                            .into(imageView);
                    // Set the title and content
                    textView.setText(artikel.getTitle());
                    textViewContent.setText(artikel.getContent());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }
}
