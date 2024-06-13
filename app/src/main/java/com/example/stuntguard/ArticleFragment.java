package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment implements ArtikelPreviewAdapter.OnArtikelClickListener, BeritaPreviewAdapter.OnBeritaClickListener {
    private RecyclerView beritaView;
    private BeritaPreviewAdapter beritaAdapter;
    private RecyclerView artikelView;
    private ArtikelPreviewAdapter artikelAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        Toolbar toolbar = view.findViewById(R.id.topbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageButton backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> requireActivity().finish());

        beritaView = view.findViewById(R.id.whatsnew);
        beritaView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        artikelView = view.findViewById(R.id.hottopics);
        artikelView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ViewCompat.setLayoutDirection(artikelView, ViewCompat.LAYOUT_DIRECTION_RTL);

        firebaseDatabase = FirebaseDatabase.getInstance("https://stuntguard-1cd62-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();

        loadBeritas();
        loadArtikels();

        return view;
    }

    private void loadBeritas() {
        databaseReference.child("beritas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Berita> beritas = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Berita berita = dataSnapshot.getValue(Berita.class);
                    beritas.add(berita);
                }
                beritaAdapter = new BeritaPreviewAdapter(beritas, ArticleFragment.this, getContext());
                beritaView.setAdapter(beritaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }

        });
    }

    private void loadArtikels() {
        databaseReference.child("artikels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Artikel> artikels = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Artikel artikel = dataSnapshot.getValue(Artikel.class);
                    artikels.add(artikel);
                }
                artikelAdapter = new ArtikelPreviewAdapter(artikels, ArticleFragment.this);
                artikelView.setAdapter(artikelAdapter);
                artikelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }

        });
    }

    @Override
    public void onArtikelClick(Artikel artikel) {
        Intent intent = new Intent(getContext(), IsiArtikelActivity.class);
        intent.putExtra("key", artikel.getKey());
        startActivity(intent);
    }

    @Override
    public void onBeritaClick(Berita berita) {
        Intent intent = new Intent(getContext(), IsiBeritaActivity.class);
        intent.putExtra("key", berita.getKey());
        startActivity(intent);
    }
}
