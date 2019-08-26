package com.electron.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.electron.R;
import com.electron.model.Discount;
import com.electron.model.Extra;

import java.util.ArrayList;

public class RecyclerViewAdapterDiscount extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
private  int flag=0;
private ArrayList<Discount> discountList;
private Context context;

    public RecyclerViewAdapterDiscount(ArrayList<Discount> discountList, Context context) {
        this.discountList = discountList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                View viewItem = inflater.inflate(R.layout.list_offer, parent, false);
                viewHolder = new RecyclerViewAdapterDiscount.MyViewHolderService(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                RecyclerViewAdapterDiscount.MyViewHolderService myViewHolder;
                Discount response=discountList.get(i);

                myViewHolder = (RecyclerViewAdapterDiscount.MyViewHolderService) viewHolder;
                myViewHolder.tv_title.setText(response.getDiscount_typename());
                myViewHolder.tv_rate.setText(response.getLabels());


    }

    @Override
    public int getItemCount() {

            return discountList.size();

    }

    protected class MyViewHolderService extends RecyclerView.ViewHolder {

        TextView tv_rate,tv_title;

        public MyViewHolderService(@NonNull View itemView) {
            super(itemView);
            tv_rate=itemView.findViewById(R.id.rate);
            tv_title=itemView.findViewById(R.id.type);

        }
    }


}
