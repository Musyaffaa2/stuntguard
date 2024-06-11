package com.example.stuntguard;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IsiArtikelActivity extends AppCompatActivity {

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

        ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.title);
        TextView textViewContent = findViewById(R.id.content);

        int imageResId = getIntent().getIntExtra("imageResId", -1);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        imageView.setImageResource(imageResId);
        textView.setText(title);
        textViewContent.setText(content);
    }
}