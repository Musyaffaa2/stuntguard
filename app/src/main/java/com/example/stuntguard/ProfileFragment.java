package com.example.stuntguard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ImageView profileImageView;
    private Button editProfileButton, logoutButton;
//    private Button favoriteButton;
    private TextView nameTextView, heightTextView, weightTextView, medicalHistoryTextView;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Toolbar toolbar = view.findViewById(R.id.topbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageButton backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> requireActivity().finish());

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        String url = getResources().getString(R.string.urlDatabase);
        databaseReference = FirebaseDatabase.getInstance(url).getReference("users");
        storageReference = FirebaseStorage.getInstance().getReference("profile_pictures");

        profileImageView = view.findViewById(R.id.profileImageView);
        editProfileButton = view.findViewById(R.id.editProfileButton);
//        favoriteButton = view.findViewById(R.id.favoriteButton);
        logoutButton = view.findViewById(R.id.logoutButton);
        nameTextView = view.findViewById(R.id.nameTextView);
        heightTextView = view.findViewById(R.id.heightTextView);
        weightTextView = view.findViewById(R.id.weightTextView);
        medicalHistoryTextView = view.findViewById(R.id.medicalHistoryTextView);

        loadProfileData();
        loadProfilePicture();

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

//        favoriteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(getActivity(), FavoriteActivity.class);
////                startActivity(intent);
//            }
//        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProfileData(); // Reload profile data when returning to this fragment
    }

    private void loadProfileData() {
        if (currentUser != null) {
            databaseReference.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String height = dataSnapshot.child("height").getValue(String.class);
                        String weight = dataSnapshot.child("weight").getValue(String.class);
                        String medicalHistory = dataSnapshot.child("medicalHistory").getValue(String.class);
                        String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);

                        nameTextView.setText(name);
                        heightTextView.setText(height);
                        weightTextView.setText(weight);
                        medicalHistoryTextView.setText(medicalHistory);

                        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
                            Glide.with(getActivity()).load(profileImageUrl).into(profileImageView);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle possible errors.
                }
            });
        }
    }

    private void loadProfilePicture() {
        if (currentUser != null) {
            // Load profile picture code here
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        if (imageUri != null) {
            // Upload profile picture code here
   }
  }
}
