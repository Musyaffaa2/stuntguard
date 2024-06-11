package com.example.stuntguard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.stuntguard.ContainerAnak;
import com.example.stuntguard.R;

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
    }


    @Override
    public int getItemCount() {
        return containerAnakList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPhoto;
        public TextView textViewName;
        public TextView textViewLastUpdated;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastUpdated = itemView.findViewById(R.id.textViewLastUpdated);
        }
    }
}
