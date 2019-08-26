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
    List<ResultAppoinment> carList;
    Context context;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private PaginationAdapterCallback mCallback;
    private String errorMsg;

    public CarAdapter(Context context,PaginationAdapterCallback mCallback) {
        this.context = context;
        this.mCallback = mCallback;
        carList = new ArrayList<>();
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


        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.recycler_list_row, parent, false);
                viewHolder = new MyViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResultAppoinment carData=carList.get(position);

        Log.e("position","1111111 "+position);

        switch (getItemViewType(position)) {

            case ITEM:
                final MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.tv_service.setText(carData.getTitle());
                myViewHolder.tv_customer.setText(carData.getStaff_names());
                myViewHolder.tv_location.setText(carData.getAddress());
                myViewHolder.tv_time.setText(carData.getAppointment_time());
                myViewHolder.tv_status.setText(carData.getBooking_status());
                Log.e("Booking status","111111 "+carData.getBooking_status());
               /* if(carData.getBooking_status().equals("A"))
                {
                    myViewHolder.tv_status.setText("Active");

                }
                else
                {
                    myViewHolder.tv_status.setText("Paid");

                }*/
                break;
            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;

                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);

                    loadingVH.mErrorTxt.setText(
                            errorMsg != null ?
                                    errorMsg :
                                    context.getString(R.string.error_msg_unknown));

                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:

                    showRetry(false, null);
                    mCallback.retryPageLoad();

                    break;
            }
        }
    }
    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(carList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }






    /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */

    public void add(ResultAppoinment r) {
        Log.d("111112",""+r.toString());
        carList.add(r);
        notifyItemInserted(carList.size() - 1);
    }

    public void addAll(List<ResultAppoinment> moveResults) {
        for (ResultAppoinment result : moveResults) {
            add(result);
        }
    }

    public void remove(ResultAppoinment r) {
        int position = carList.indexOf(r);
        if (position > -1) {
            carList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ResultAppoinment());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = carList.size() - 1;
        ResultAppoinment result = getItem(position);

        if (result != null) {
            carList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ResultAppoinment getItem(int position) {
        return carList.get(position);
    }

    /**
     * Displays Pagination retry footer view along with appropriate errorMsg
     *
     * @param show
     * @param errorMsg to display if page load fails
     */

}
