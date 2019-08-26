package com.electron.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.electron.R;
import com.electron.model.Method;
import com.electron.model.Unit;

import java.util.ArrayList;

public class RecyclerAdapterUnit extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  ArrayList<Unit> unitList;
    private Context context;
    int minteger = 0;

    public RecyclerAdapterUnit( ArrayList<Unit> unitList, Context context) {
        this.unitList = unitList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewLoading = inflater.inflate(R.layout.list_unit, parent, false);
        viewHolder = new MyViewHolderMethod(viewLoading);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolderMethod myViewHolderMethod;
        Unit unit=unitList.get(i);

        myViewHolderMethod = (MyViewHolderMethod) viewHolder;
        myViewHolderMethod.tv_unitText.setText(unit.getLimit_title());
        myViewHolderMethod.bt_inc.setOnClickListener(v -> {
            minteger = minteger + 1;
            myViewHolderMethod.tv_unit.setText("" + minteger);
        });
        myViewHolderMethod.bt_dec.setOnClickListener(v -> {
            minteger = minteger - 1;
            myViewHolderMethod.tv_unit.setText("" + minteger);
        });

    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }
    protected class MyViewHolderMethod extends RecyclerView.ViewHolder {

        TextView tv_unitText,tv_unit;
        Button bt_inc,bt_dec;


        public MyViewHolderMethod(@NonNull View itemView) {
            super(itemView);
            tv_unitText=itemView.findViewById(R.id.unit_title);
            tv_unit=itemView.findViewById(R.id.integer_number);
            bt_inc=itemView.findViewById(R.id.increase);
            bt_dec=itemView.findViewById(R.id.decrease);



        }
    }
}
