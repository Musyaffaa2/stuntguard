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
import java.util.ArrayList;
import java.util.List;

public class ContainerAnakAdapter extends RecyclerView.Adapter<ContainerAnakAdapter.ViewHolder> {

    private List<Child> childList;
    private Context context;
    private OnChildClickListener onChildClickListener;

    public interface OnChildClickListener {
        void onChildClick(Child child);
    }

    public ContainerAnakAdapter(Context context, List<Child> childList, OnChildClickListener onChildClickListener) {
        this.context = context;
        this.childList = childList != null ? childList : new ArrayList<>();
        this.onChildClickListener = onChildClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anak, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Child child = childList.get(position);
        Log.d("ContainerAnakAdapter", "Binding view at position: " + position + " with data: " + child.getName());
        holder.textViewName.setText(child.getName());
        holder.textViewLastUpdated.setText(child.getLastUpdated());

        // Load image using Glide with circular transformation
        Glide.with(context)
                .load(child.getChildImageUrl()) // Use child's image URL
                .placeholder(R.mipmap.abe_round) // Placeholder image while loading
                .error(R.mipmap.abe_round) // Image to display if loading fails
                .into(holder.imageViewPhoto);

        holder.bind(child, onChildClickListener);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPhoto;
        public TextView textViewName;
        public TextView textViewLastUpdated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastUpdated = itemView.findViewById(R.id.textViewLastUpdated);
        }

        public void bind(final Child child, final OnChildClickListener listener) {
            itemView.setOnClickListener(v -> listener.onChildClick(child));
        }
    }

    // Cleanup method to remove ValueEventListener when adapter is no longer needed
    public void cleanup() {
        // Remove ValueEventListener if implemented
    }
}
