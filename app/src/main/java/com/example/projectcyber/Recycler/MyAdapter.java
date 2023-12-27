package com.example.projectcyber.Recycler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.R;
import com.example.projectcyber.RetrofitClientInstance;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyHolderProduct>
{
    Context context;
    List<ItemProduct> items;
    Activity activity;
    private ItemClickListener myItemListener;

    public MyAdapter(Context context, List<ItemProduct> items, ItemClickListener myItemListener,Activity activity) {
        this.context = context;
        this.items = items;
        this.activity = activity;
        this.myItemListener = myItemListener;
    }
    public interface ItemClickListener{
        void onItemClick(ItemProduct myItem);
        void onItemLongClick(ItemProduct myItem);
    }

    public void setMyItemListener(ItemClickListener myItemListener) {
        this.myItemListener = myItemListener;
    }

    @NonNull
    @Override
    public MyHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new MyHolderProduct(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolderProduct holder, int position) {
        holder.nameView.setText(items.get(holder.getAdapterPosition()).getName());
        new RetrofitClientInstance.ImageBitmapUriTask(activity,holder.imageView).execute(RetrofitClientInstance.BASE_URL+"/product/" + items.get(position).getId());
        holder.priceView.setText(String.valueOf(items.get(holder.getAdapterPosition()).getPrice()));

        holder.itemView.setOnClickListener(view->{
            myItemListener.onItemClick(items.get(holder.getAdapterPosition()));
        });
        holder.itemView.setOnLongClickListener(view->{
            myItemListener.onItemLongClick(items.get(holder.getAdapterPosition()));
            return true;
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
