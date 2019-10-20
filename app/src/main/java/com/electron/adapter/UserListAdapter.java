package com.electron.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electron.R;
import com.electron.model.ResultAppoinment;
import com.electron.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User> userList;
    private Context context;



    public UserListAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList =userList;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.name);
            tv_phone=itemView.findViewById(R.id.phone);

        }
    }



    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.list_user, parent, false);
        viewHolder = new MyViewHolder(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User carData=userList.get(position);

        Log.e("position","1111115 "+position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_name.setText(carData.getFirst_name()+" "+carData.getLast_name());
        myViewHolder.tv_phone.setText(carData.getPhone());

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }



}
