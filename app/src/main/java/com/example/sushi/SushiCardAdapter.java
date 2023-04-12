package com.example.sushi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SushiCardAdapter extends RecyclerView.Adapter<SushiCardHolder> {
    private Context context;
    private  List<SushiCard> sushiCards;

    public  SushiCardAdapter(Context context, List<SushiCard> sushiCards){
        this.sushiCards=sushiCards;
        this.context=context;
    }

    @Override
    public SushiCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.sushi_card,null,false);
        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(params);

        SushiCardHolder rcv=new SushiCardHolder((layoutView));
        return rcv;
    }

    @Override
    public void onBindViewHolder(SushiCardHolder holder, @SuppressLint("RecyclerView") int position) {
        DBConnection db=new DBConnection(holder.itemView.getContext());

        holder.name.setText(sushiCards.get(position).getName());
        holder.quantity.setText(sushiCards.get(position).getQuantity()+"");
        holder.price.setText(sushiCards.get(position).getCost()+".0MDL");
        holder.image.setImageResource(sushiCards.get(position).getPhoto());

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addOneSushi(sushiCards.get(position).getID(),sushiCards.get(position).getQuantity());
                sushiCards.get(position).setQuantity(sushiCards.get(position).getQuantity()+1);
                holder.quantity.setText(sushiCards.get(position).getQuantity()+"");
                MainActivity.calculateTotalCost();
            }
        });

        holder.substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removeOneSushi(sushiCards.get(position).getID(),sushiCards.get(position).getQuantity());
                sushiCards.get(position).setQuantity(sushiCards.get(position).getQuantity()<=1?0:sushiCards.get(position).getQuantity()-1);
                holder.quantity.setText(sushiCards.get(position).getQuantity()+"");
                MainActivity.calculateTotalCost();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sushiCards.size();
    }
}
