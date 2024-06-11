package com.example.stuntguard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MonitorFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContainerAnakAdapter adapter;
    private List<ContainerAnak> containerAnakList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize your containerAnakList
        containerAnakList = new ArrayList<>();
        containerAnakList.add(new ContainerAnak(1, "Abe", "Last Updated: 01/01/2022", "https://i.pinimg.com/736x/e5/51/c1/e551c1f97495380bbf0162048a8931ca.jpg"));
        containerAnakList.add(new ContainerAnak(2, "Kumar", "Last Updated: 01/01/2022", "https://i.pinimg.com/736x/9a/37/07/9a37075206ca9446f7b8b5905ae856ac.jpg"));

        // Add more items if needed

        // Initialize adapter with the list
        adapter = new ContainerAnakAdapter(getActivity(), containerAnakList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}