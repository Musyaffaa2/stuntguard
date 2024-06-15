
package com.example.stuntguard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonitorFragment extends Fragment {

    private static final int INSERT_CHILD_REQUEST = 1;

    private RecyclerView recyclerView;
    private ChildAdapter adapter;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);

        Button tambahanak = view.findViewById(R.id.buttonTambahAnak);
        tambahanak.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InsertChildActivity.class);
            startActivityForResult(intent, INSERT_CHILD_REQUEST);
        });

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            loadChildrenData(userId);
        }

        return view;
    }

    private void loadChildrenData(String userId) {
        String url = getResources().getString(R.string.urlDatabase);
        databaseReference = FirebaseDatabase.getInstance(url).getReference("children").child(userId);

        FirebaseRecyclerOptions<Child> options =
                new FirebaseRecyclerOptions.Builder<Child>()
                        .setQuery(databaseReference, Child.class)
                        .build();

        adapter = new ChildAdapter(options, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INSERT_CHILD_REQUEST && resultCode == Activity.RESULT_OK) {
            // Perbarui RecyclerView
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}


