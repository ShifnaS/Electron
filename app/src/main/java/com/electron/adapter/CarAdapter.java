package com.electron.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.electron.model.Result;
import com.electron.R;
import com.electron.model.ResultAppoinment;
import com.electron.utils.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;


public class CarAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ResultAppoinment> carList;
    private Context context;



    public CarAdapter(Context context,List<ResultAppoinment> carList) {
        this.context = context;
        this.carList =carList;
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_service,tv_location,tv_customer,tv_status,tv_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_service=itemView.findViewById(R.id.name);
            tv_location=itemView.findViewById(R.id.carname);
            tv_customer=itemView.findViewById(R.id.carnum);
            tv_status=itemView.findViewById(R.id.advisor);
            tv_time=itemView.findViewById(R.id.technician);
        }
    }



    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.recycler_list_row, parent, false);
        viewHolder = new MyViewHolder(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResultAppoinment carData=carList.get(position);

        Log.e("position","1111115 "+position);
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_service.setText(carData.getTitle());
        myViewHolder.tv_customer.setText(carData.getStaff_names());
        myViewHolder.tv_location.setText(carData.getAddress());
        myViewHolder.tv_time.setText(carData.getAppointment_time());
      //  myViewHolder.tv_status.setText(carData.getBooking_status());
       // A=active, C=confirm, R=Reject, CC=Cancel by Client, CS=Cancel by service provider,CO=Completed,MN=MARK AS NOSHOW
          Log.e("Booking status","111111 "+carData.getBooking_status());
                if(carData.getBooking_status().equals("A"))
                {
                    myViewHolder.tv_status.setText("Active");

                }
                else
                {
                    myViewHolder.tv_status.setText("XYZ");

                }

    }


    @Override
    public int getItemCount() {
        return carList.size();
    }



}
