package com.example.stuntguard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<Menu> menus;
    private OnMenuClickListener onMenuClickListener;

    public interface OnMenuClickListener {
        void onMenuClick(Menu menu);
    }

    public MenuAdapter(List<Menu> menus, OnMenuClickListener onMenuClickListener) {
        this.menus = menus;
        this.onMenuClickListener = onMenuClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = menus.get(position);
        Glide.with(holder.imageView.getContext()).load(menu.getImageUrl()).into(holder.imageView);
        holder.textViewTitle.setText(menu.getTitle());
        holder.bind(menu, onMenuClickListener);
    }

    @Override
    public int getItemCount() { return menus.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        public void bind(final Menu menu, final MenuAdapter.OnMenuClickListener listener) {
            itemView.setOnClickListener(v -> listener.onMenuClick(menu));
        }
    }
}
