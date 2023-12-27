package com.example.projectcyber.Recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcyber.R;

public class MyHolderProduct extends RecyclerView.ViewHolder
{
    TextView nameView;
    ImageView imageView;
    TextView priceView;

    public MyHolderProduct(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.nameTextViewProduct);
        imageView = itemView.findViewById(R.id.imageViewProduct);
        priceView = itemView.findViewById(R.id.priceTextViewProduct);
    }
}
