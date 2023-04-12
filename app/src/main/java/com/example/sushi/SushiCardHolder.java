package com.example.sushi;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SushiCardHolder extends RecyclerView.ViewHolder  {
    public TextView name;
    public TextView quantity;
    public Button add;

    public Button substract;

    public ImageView image;
    public TextView price;

    public SushiCardHolder(View itemView) {
        super(itemView);

        name=(TextView) itemView.findViewById(R.id.name);
        quantity=(TextView) itemView.findViewById(R.id.quantity);
        add=(Button) itemView.findViewById(R.id.add);
        substract=(Button) itemView.findViewById(R.id.substract);
        image=(ImageView) itemView.findViewById(R.id.image);
        price=(TextView) itemView.findViewById(R.id.price);
    }
}
