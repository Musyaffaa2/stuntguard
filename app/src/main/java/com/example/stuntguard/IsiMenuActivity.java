package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;

public class IsiMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_menu);

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

        // Find views
        ImageView imageView = findViewById(R.id.image);
        TextView Title = findViewById(R.id.title);
        TextView textViewRecipe = findViewById(R.id.recipe);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String recipe = intent.getStringExtra("recipe");

        Title.setText(title);
        textViewRecipe.setText(recipe);

        String imageUrl = intent.getStringExtra("imageUrl");
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }
}
