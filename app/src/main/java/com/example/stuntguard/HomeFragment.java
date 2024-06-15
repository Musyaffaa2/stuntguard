package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MenuAdapter.OnMenuClickListener {
    private RecyclerView menuView;
    private MenuAdapter menuAdapter;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MaterialButton monitorButton = view.findViewById(R.id.monitorButton);
        MaterialButton articleButton = view.findViewById(R.id.articleButton);
        MaterialButton profileButton = view.findViewById(R.id.profileButton);
        MaterialButton childButton = view.findViewById(R.id.childButton);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        monitorButton.setOnClickListener(v -> bottomNavigationView.setSelectedItemId(R.id.monitor));
        articleButton.setOnClickListener(v -> bottomNavigationView.setSelectedItemId(R.id.article));
        profileButton.setOnClickListener(v -> bottomNavigationView.setSelectedItemId(R.id.profile));
        childButton.setOnClickListener(v -> bottomNavigationView.setSelectedItemId(R.id.monitor));

        menuView = view.findViewById(R.id.menu);
        menuView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        String url = getResources().getString(R.string.urlDatabase);
        firebaseDatabase = FirebaseDatabase.getInstance(url);
        databaseReference = firebaseDatabase.getReference();

        loadMenus();

        return view;
    }

    private void loadMenus() {
        databaseReference.child("menus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Menu> menus = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Menu menu = dataSnapshot.getValue(Menu.class);
                    menus.add(menu);
                }
                menuAdapter = new MenuAdapter(menus, HomeFragment.this);
                menuView.setAdapter(menuAdapter);
                menuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }

        });
    }

    @Override
    public void onMenuClick(Menu menu) {
        Intent intent = new Intent(getContext(), IsiMenuActivity.class);
        intent.putExtra("imageUrl", menu.getImageUrl());
        intent.putExtra("title", menu.getTitle());
        intent.putExtra("resep", menu.getResep());
        startActivity(intent);
    }

}