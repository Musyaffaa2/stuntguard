package com.example.stuntguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MonitorFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContainerAnakAdapter adapter;
    private List<ContainerAnak> containerAnakList;
    private Button tambahanak;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tambahanak = view.findViewById(R.id.buttonTambahAnak);

        // Initialize your containerAnakList
        containerAnakList = new ArrayList<>();
        containerAnakList.add(new ContainerAnak(1, "Abe", "Last Updated: 01/01/2022", "https://i.pinimg.com/736x/e5/51/c1/e551c1f97495380bbf0162048a8931ca.jpg", 13.7, 112.3, 50, 5));
        containerAnakList.add(new ContainerAnak(2, "Kumar", "Last Updated: 01/01/2022", "https://i.pinimg.com/736x/9a/37/07/9a37075206ca9446f7b8b5905ae856ac.jpg", 22.5, 150, 70, 6));

        // Add more items if needed

        // Initialize adapter with the list
        adapter = new ContainerAnakAdapter(getActivity(), containerAnakList);
        recyclerView.setAdapter(adapter);

        // Set up click listener for the button to navigate to InsertChildActivity
        tambahanak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertChildActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
