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
    ArrayList<ListDomain> listDomains;

    public NewAdapter(ArrayList<ListDomain> listDomains) {
        this.listDomains = listDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.feederName.setText(listDomains.get(position).getTitle());
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listDomains.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), Menus.class);
                intent.putExtra("itemImage", drawableResourceId);
                holder.itemView.getContext().startActivity(intent);
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



