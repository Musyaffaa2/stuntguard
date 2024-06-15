package com.example.stuntguard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ChildAdapter extends FirebaseRecyclerAdapter<Child, ChildAdapter.ChildViewHolder> {

    private Context context;

    public ChildAdapter(@NonNull FirebaseRecyclerOptions<Child> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChildViewHolder holder, int position, @NonNull Child model) {
        holder.textViewName.setText(model.getName());
//        String Age = String.valueOf(model.getAge());
        int tahun = model.getAge()/12;
        int bulan = model.getAge()%12;
        String Age = String.valueOf(String.valueOf(tahun) + " Tahun " + String.valueOf(bulan) + " Bulan");
        holder.textViewLastUpdated.setText(Age);
        // Load image using Picasso
        Picasso.get().load(model.getChildImageUrl()).into(holder.imageViewPhoto);
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anak, parent, false);
        return new ChildViewHolder(view);
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewLastUpdated;
        ImageView imageViewPhoto;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastUpdated = itemView.findViewById(R.id.textViewLastUpdated);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
        }
    }
}



