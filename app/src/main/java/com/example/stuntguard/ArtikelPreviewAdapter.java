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

public class ArtikelPreviewAdapter extends RecyclerView.Adapter<ArtikelPreviewAdapter.ViewHolder> {

    private List<Artikel> artikels;
    private OnArtikelClickListener onArtikelClickListener;

    public interface OnArtikelClickListener {
        void onArtikelClick(Artikel artikel);
    }

    public ArtikelPreviewAdapter(List<Artikel> artikels, OnArtikelClickListener onArtikelClickListener) {
        this.artikels = artikels;
        this.onArtikelClickListener = onArtikelClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artikel artikel = artikels.get(position);
        Glide.with(holder.imageView.getContext()).load(artikel.getImageUrl()).into(holder.imageView);
        holder.textViewTitle.setText(artikel.getTitle());
        holder.bind(artikel, onArtikelClickListener);
    }

    @Override
    public int getItemCount() {
        return artikels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        public void bind(final Artikel artikel, final OnArtikelClickListener listener) {
            itemView.setOnClickListener(v -> listener.onArtikelClick(artikel));
        }
    }
}
