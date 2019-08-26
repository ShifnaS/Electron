package com.electron.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electron.R;
import com.electron.model.Method;

import java.util.ArrayList;

public class RecyclerAdapterMethod extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Method> methodList;
    private Context context;

    public RecyclerAdapterMethod(ArrayList<Method> methodList, Context context) {
        this.methodList = methodList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewLoading = inflater.inflate(R.layout.list_item, parent, false);
        viewHolder = new MyViewHolderMethod(viewLoading);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolderMethod myViewHolderMethod;
        Method response1=methodList.get(i);

        myViewHolderMethod = (MyViewHolderMethod) viewHolder;
        myViewHolderMethod.tv_service.setText(response1.getMethod_title());
    }

    @Override
    public int getItemCount() {
        return methodList.size();
    }
    protected class MyViewHolderMethod extends RecyclerView.ViewHolder {

        TextView tv_service;

        public MyViewHolderMethod(@NonNull View itemView) {
            super(itemView);
            tv_service=itemView.findViewById(R.id.service);

        }
    }
}
