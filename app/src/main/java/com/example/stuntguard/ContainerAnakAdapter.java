package com.example.stuntguard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ContainerAnakAdapter extends RecyclerView.Adapter<ContainerAnakAdapter.ViewHolder> {
    private List<ContainerAnak> containerAnakList;
    private Context context;

    public ContainerAnakAdapter(Context context, List<ContainerAnak> containerAnakList) {
        this.context = context;
        this.containerAnakList = containerAnakList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anak, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContainerAnak containerAnak = containerAnakList.get(position);
        Log.d("ContainerAnakAdapter", "Binding view at position: " + position + " with data: " + containerAnak.getName());

        holder.textViewName.setText(containerAnak.getName());
        holder.textViewLastUpdated.setText(containerAnak.getLastUpdated());

        // Load image into ImageView using Glide
        Glide.with(context)
                .load(containerAnak.getImageUrl())
                .placeholder(R.mipmap.abe_round) // Placeholder image while loading
                .error(R.mipmap.abe_round) // Image to display if loading fails
                .into(holder.imageViewPhoto);

        holder.bind(containerAnak);
    }

    @Override
    public int getItemCount() {
        return containerAnakList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageViewPhoto;
        public TextView textViewName;
        public TextView textViewLastUpdated;
        private ContainerAnak containerAnak;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastUpdated = itemView.findViewById(R.id.textViewLastUpdated);
            itemView.setOnClickListener(this);
        }

        public void bind(ContainerAnak containerAnak) {
            this.containerAnak = containerAnak;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailAnak.class);
            intent.putExtra("id", containerAnak.getId());
            intent.putExtra("name", containerAnak.getName());
            intent.putExtra("lastUpdated", containerAnak.getLastUpdated());
            intent.putExtra("imageUrl", containerAnak.getImageUrl());
            intent.putExtra("usia", containerAnak.getUsia());  // Pass usia
            intent.putExtra("beratBadan", containerAnak.getBeratBadan());  // Pass beratBadan
            intent.putExtra("tinggiBadan", containerAnak.getTinggiBadan());  // Pass tinggiBadan
            intent.putExtra("lingkarKepala", containerAnak.getLingkarKepala());  // Pass lingkarKepala
            context.startActivity(intent);
        }
    }
}
