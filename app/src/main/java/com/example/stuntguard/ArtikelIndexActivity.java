package com.example.stuntguard;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ArtikelIndexActivity extends AppCompatActivity implements ArtikelPreviewAdapter.OnArtikelClickListener, BeritaPreviewAdapter.OnBeritaClickListener  {
    private RecyclerView beritaView;
    private BeritaPreviewAdapter BeritaAdapter;
    private RecyclerView artikelView;
    private ArtikelPreviewAdapter ArtikelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_index);;

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

        beritaView = findViewById(R.id.whatsnew);
        beritaView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Berita> beritas = generateSampleBeritas();
        BeritaAdapter = new BeritaPreviewAdapter(beritas, this);
        beritaView.setAdapter(BeritaAdapter);

        artikelView = findViewById(R.id.hottopics);
        artikelView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ViewCompat.setLayoutDirection(artikelView, ViewCompat.LAYOUT_DIRECTION_RTL);

        List<Artikel> artikels = generateSampleArtikels();
        ArtikelAdapter = new ArtikelPreviewAdapter(artikels, this);
        artikelView.setAdapter(ArtikelAdapter);
    }

    @Override
    public void onArtikelClick(Artikel artikel) {
        Intent intent = new Intent(this, IsiArtikelActivity.class);
        intent.putExtra("imageResId", artikel.getImageResource());
        intent.putExtra("title", artikel.getTitle());
        intent.putExtra("content", artikel.getContent());
        startActivity(intent);
    }

    @Override
    public void onBeritaClick(Berita berita) {
        Intent intent = new Intent(this, IsiBeritaActivity.class);
        intent.putExtra("imageResId", berita.getImageResource());
        intent.putExtra("title", berita.getTitle());
        intent.putExtra("content", berita.getContent());
        startActivity(intent);
    }

    private List<Berita> generateSampleBeritas() {
        List<Berita> beritas = new ArrayList<>();

        // Tambahkan artikel di sini
        beritas.add(new Berita(R.drawable.article_image1, "15 Tips agar Janin dalam Kandungan Sehat dan Sempurna", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel mauris dictum, dictum lacus eu, tincidunt nulla. In hac habitasse platea dictumst. Nunc ut augue sed ante semper accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet accumsan purus, non tincidunt metus consequat a. Ut mollis, libero sed suscipit placerat, elit mauris maximus leo, eget pharetra arcu quam in odio. In volutpat, nunc sit amet pharetra imperdiet, nisi erat tristique velit, sit amet congue ipsum dui ut ante. Vivamus eu sapien ac magna fermentum tempor eu interdum ante. Donec laoreet neque hendrerit eleifend maximus."));
        beritas.add(new Berita(R.drawable.article_image2, "Kadar Hb Normal pada Ibu Hamil dan Cara Menjaganya", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel mauris dictum, dictum lacus eu, tincidunt nulla. In hac habitasse platea dictumst. Nunc ut augue sed ante semper accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet accumsan purus, non tincidunt metus consequat a. Ut mollis, libero sed suscipit placerat, elit mauris maximus leo, eget pharetra arcu quam in odio. In volutpat, nunc sit amet pharetra imperdiet, nisi erat tristique velit, sit amet congue ipsum dui ut ante. Vivamus eu sapien ac magna fermentum tempor eu interdum ante. Donec laoreet neque hendrerit eleifend maximus."));
        beritas.add(new Berita(R.drawable.article_image3, "Manfaat Vitamin K untuk Mendukung Program Kehamilan", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel mauris dictum, dictum lacus eu, tincidunt nulla. In hac habitasse platea dictumst. Nunc ut augue sed ante semper accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet accumsan purus, non tincidunt metus consequat a. Ut mollis, libero sed suscipit placerat, elit mauris maximus leo, eget pharetra arcu quam in odio. In volutpat, nunc sit amet pharetra imperdiet, nisi erat tristique velit, sit amet congue ipsum dui ut ante. Vivamus eu sapien ac magna fermentum tempor eu interdum ante. Donec laoreet neque hendrerit eleifend maximus."));
        return beritas;
    }
    private List<Artikel> generateSampleArtikels() {
        List<Artikel> artikels = new ArrayList<>();

        // Tambahkan artikel di sini
        artikels.add(new Artikel(R.drawable.article_image1, "15 Tips agar Janin dalam Kandungan Sehat dan Sempurna","Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel mauris dictum, dictum lacus eu, tincidunt nulla. In hac habitasse platea dictumst. Nunc ut augue sed ante semper accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet accumsan purus, non tincidunt metus consequat a. Ut mollis, libero sed suscipit placerat, elit mauris maximus leo, eget pharetra arcu quam in odio. In volutpat, nunc sit amet pharetra imperdiet, nisi erat tristique velit, sit amet congue ipsum dui ut ante. Vivamus eu sapien ac magna fermentum tempor eu interdum ante. Donec laoreet neque hendrerit eleifend maximus."));
        artikels.add(new Artikel(R.drawable.article_image2, "Kadar Hb Normal pada Ibu Hamil dan Cara Menjaganya","Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel mauris dictum, dictum lacus eu, tincidunt nulla. In hac habitasse platea dictumst. Nunc ut augue sed ante semper accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet accumsan purus, non tincidunt metus consequat a. Ut mollis, libero sed suscipit placerat, elit mauris maximus leo, eget pharetra arcu quam in odio. In volutpat, nunc sit amet pharetra imperdiet, nisi erat tristique velit, sit amet congue ipsum dui ut ante. Vivamus eu sapien ac magna fermentum tempor eu interdum ante. Donec laoreet neque hendrerit eleifend maximus."));
        artikels.add(new Artikel(R.drawable.article_image3, "Manfaat Vitamin K untuk Mendukung Program Kehamilan","Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vel mauris dictum, dictum lacus eu, tincidunt nulla. In hac habitasse platea dictumst. Nunc ut augue sed ante semper accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet accumsan purus, non tincidunt metus consequat a. Ut mollis, libero sed suscipit placerat, elit mauris maximus leo, eget pharetra arcu quam in odio. In volutpat, nunc sit amet pharetra imperdiet, nisi erat tristique velit, sit amet congue ipsum dui ut ante. Vivamus eu sapien ac magna fermentum tempor eu interdum ante. Donec laoreet neque hendrerit eleifend maximus."));
        return artikels;
    }
}