package com.example.projectcyber.Recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.R;

public class OrderHolder extends RecyclerView.ViewHolder
{
    TextView idView;
    TextView priceOrderView;
    TextView statusView;

    public OrderHolder(@NonNull View itemView) {
        super(itemView);
        idView = itemView.findViewById(R.id.textOrderid);
        priceOrderView = itemView.findViewById(R.id.textOrderprice);
        statusView = itemView.findViewById(R.id.textStatusorder);
    }
}
