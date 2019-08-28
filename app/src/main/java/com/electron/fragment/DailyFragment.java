package com.electron.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.electron.activity.AppoinmentActivity;
import com.electron.activity.ServiceDetailActivity;
import com.electron.adapter.CarAdapter;
import com.electron.adapter.RecyclerTouchListener;
import com.electron.api.APIService;
import com.electron.api.ApiModule;
import com.electron.model.Result;
import com.electron.model.ResultAppoinment;
import com.electron.model.ResultList;
import com.electron.utils.Constants;
import com.electron.utils.NetworkUtil;
import com.electron.utils.PaginationAdapterCallback;
import com.electron.utils.PaginationScrollListener;
import com.electron.utils.SharedPreferenceUtils;
import com.electron.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyFragment extends Fragment implements PaginationAdapterCallback {

    private static final String TAG = "DailyFragment";
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private LinearLayout errorLayout;
    private Button btnRetry;
    private TextView txtError;

    private List<ResultAppoinment> carList = new ArrayList();
    private CarAdapter mAdapter ;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private String service = "";
    private int userId;
    private APIService apiService;


    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_services, paramViewGroup, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        userId = SharedPreferenceUtils.getInstance(getContext()).getIntValue(Constants.KEY_USER_ID);

        progressBar =view.findViewById(R.id.main_progress);
        errorLayout = view.findViewById(R.id.error_layout);
        btnRetry = view.findViewById(R.id.error_btn_retry);
        txtError = view.findViewById(R.id.error_txt_cause);



        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        apiService = ApiModule.getAPIService();

        loadFirstPage();

        btnRetry.setOnClickListener((View view1) -> {
            loadFirstPage();
        });

        swipeRefreshLayout.setOnRefreshListener(this::doRefresh);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            public void onClick(View param1View, int param1Int) {
                ResultAppoinment result = carList.get(param1Int);
                String order_id=result.getOrder_id();
                Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                intent.putExtra("order_id", order_id);
                startActivity(intent);
            }

            public void onLongClick(View param1View, int param1Int) {}
        }));



        return view;
    }

    private void doRefresh() {
        progressBar.setVisibility(View.VISIBLE);
        if (callApi().isExecuted())
            callApi().cancel();

        // TODO: Check if data is stale.
        //  Execute network request if cache is expired; otherwise do not update data.
        carList.clear();
        mAdapter.notifyDataSetChanged();
        loadFirstPage();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");

        // To ensure list is visible when retry button in error view is clicked
        hideErrorView();

        callApi().enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                hideErrorView();

                carList=response.body().getResponse();
                progressBar.setVisibility(View.GONE);
                mAdapter = new CarAdapter(getActivity(),carList);
                recyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                t.printStackTrace();
                showErrorView(t);
            }
        });
    }



    @Override
    public void retryPageLoad() {
        loadFirstPage();
    }

    private Call<ResultList> callApi() {
        return apiService.getOpenedCarList("get_all_upcoming_appointment", 1,userId,"staff", 1);
    }


    private String fetchErrorMessage(Throwable throwable) {
        String errorMsg = getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkConnected()) {
            errorMsg = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMsg = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }

    private void showErrorView(Throwable throwable) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(fetchErrorMessage(throwable));
        }
    }

    private void hideErrorView() {
        if (errorLayout.getVisibility() == View.VISIBLE) {
            errorLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}



