package com.example.projectcyber.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.R;
import com.example.projectcyber.RetrofitClientInstance;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder>
{
    Context context;
    List<Order> items;

    public OrderAdapter(Context context, List<Order> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new OrderHolder(LayoutInflater.from(context).inflate(R.layout.item_view_order,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        holder.idView.setText(items.get(holder.getAdapterPosition()).getId()+"");
        holder.priceOrderView.setText(items.get(holder.getAdapterPosition()).getPriceorder()+"");
        holder.statusView.setText(String.valueOf(items.get(holder.getAdapterPosition()).getStatus()));
    }
    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
