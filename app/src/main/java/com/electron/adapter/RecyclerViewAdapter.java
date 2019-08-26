package com.electron.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.electron.R;
import com.electron.model.Method;
import com.electron.model.ResultAppoinment;
import com.electron.model.ServiceResponse;

import java.util.ArrayList;

public class RecyclerViewAdapter  extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
private  int flag=0;
private ArrayList<ServiceResponse> serviceList;
private Context context;

    public RecyclerViewAdapter(int flag, ArrayList<ServiceResponse> serviceList, Context context) {
        this.flag = flag;
        this.serviceList = serviceList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                View viewItem = inflater.inflate(R.layout.list_service, parent, false);
                viewHolder = new RecyclerViewAdapter.MyViewHolderService(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        RecyclerViewAdapter.MyViewHolderService myViewHolder;
                ServiceResponse response=serviceList.get(i);

                myViewHolder = (RecyclerViewAdapter.MyViewHolderService) viewHolder;
                myViewHolder.tv_service.setText(response.getTitle());
                Glide.with(context)
                        .load(response.getImage())
                        .into(myViewHolder.imageView);


    }

    @Override
    public int getItemCount() {

            return serviceList.size();

    }

    protected class MyViewHolderService extends RecyclerView.ViewHolder {

        TextView tv_service;
        ImageView imageView;

        public MyViewHolderService(@NonNull View itemView) {
            super(itemView);
            tv_service=itemView.findViewById(R.id.service);
            imageView=itemView.findViewById(R.id.img);

        }
    }


}
