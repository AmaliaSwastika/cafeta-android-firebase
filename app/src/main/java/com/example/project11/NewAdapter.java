package com.example.project11;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    private ArrayList<ListDomain> listDomains;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private OnItemClickListener onItemClickListener;

    public NewAdapter(ArrayList<ListDomain> listDomains, RecyclerView recyclerView1, RecyclerView recyclerView2, OnItemClickListener onItemClickListener) {
        this.listDomains = listDomains;
        this.recyclerView1 = recyclerView1;
        this.recyclerView2 = recyclerView2;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListDomain listDomain = listDomains.get(position);
        holder.feederName.setText(listDomain.getTitle());
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listDomain.getUrl(), "drawable", holder.itemView.getContext().getPackageName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = holder.getAdapterPosition();
                if (view.getParent() == recyclerView1) {
                    // RecyclerView pertama
                    onItemClickListener.onItemClick(recyclerView1, itemPosition);
                } else if (view.getParent() == recyclerView2) {
                    // RecyclerView kedua
                    onItemClickListener.onItemClick(recyclerView2, itemPosition);
                }
            }
        });

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.removeItem);
    }

    @Override
    public int getItemCount() {
        return listDomains.size();
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView feederName;
        ImageView removeItem;

        public ViewHolder(View itemView) {
            super(itemView);
            feederName = itemView.findViewById(R.id.feederName);
            removeItem = itemView.findViewById(R.id.removeFeeder);
}
}
}