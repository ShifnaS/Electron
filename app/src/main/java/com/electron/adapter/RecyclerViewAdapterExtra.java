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
import com.electron.model.Extra;
import com.electron.model.ServiceResponse;

import java.util.ArrayList;

public class RecyclerViewAdapterExtra extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
private  int flag=0;
private ArrayList<Extra> extraList;
private Context context;

    public RecyclerViewAdapterExtra( ArrayList<Extra> extraList, Context context) {
        this.extraList = extraList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                View viewItem = inflater.inflate(R.layout.list_extras, parent, false);
                viewHolder = new RecyclerViewAdapterExtra.MyViewHolderService(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        RecyclerViewAdapterExtra.MyViewHolderService myViewHolder;
                Extra response=extraList.get(i);

                myViewHolder = (RecyclerViewAdapterExtra.MyViewHolderService) viewHolder;
                myViewHolder.tv_service.setText(response.getAddon_service_name());
                Glide.with(context)
                        .load(response.getImage())
                        .into(myViewHolder.imageView);

      /*  Glide.with(context)
                .load( "http://electron-pestcontrol.akstech.com.sg/assets/images/services/service_45421.jpg")
                .into(myViewHolder.imageView);
*/

    }

    @Override
    public int getItemCount() {

            return extraList.size();

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
