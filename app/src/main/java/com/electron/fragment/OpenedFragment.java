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
import com.electron.adapter.CarAdapter;
import com.electron.adapter.RecyclerTouchListener;
import com.electron.api.APIService;
import com.electron.api.ApiModule;
import com.electron.model.Result;
import com.electron.model.ResultAppoinment;
import com.electron.model.ResultList;
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

public class OpenedFragment extends Fragment implements PaginationAdapterCallback {

    private static final String TAG = "OpenedFragment";
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private LinearLayout errorLayout;
    private Button btnRetry;
    private TextView txtError;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private static final int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;


    private List<ResultAppoinment> carList = new ArrayList();
    private CarAdapter mAdapter ;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private String service = "";
    private int userId;
    private APIService apiService;
    private PaginationAdapterCallback mCallback;
    private FloatingActionButton fab;


    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_services, paramViewGroup, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        userId = SharedPreferenceUtils.getInstance(getContext()).getIntValue("userId");

        progressBar =view.findViewById(R.id.main_progress);
        errorLayout = view.findViewById(R.id.error_layout);
        btnRetry = view.findViewById(R.id.error_btn_retry);
        txtError = view.findViewById(R.id.error_txt_cause);
        fab=view.findViewById(R.id.fab);
        mAdapter = new CarAdapter(getActivity(),mCallback);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        apiService = ApiModule.getAPIService();

        loadFirstPage();

        btnRetry.setOnClickListener((View view1) -> {
            loadFirstPage();
        });

        swipeRefreshLayout.setOnRefreshListener(this::doRefresh);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We are showing only toast message. However, you can do anything you need.
               /// Toast.makeText(getContext(), "You clicked Floating Action Button", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(), AppoinmentActivity.class);
                startActivity(i);
            }
        });



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
        currentPage = PAGE_START;

        callApi().enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                hideErrorView();
                Log.e("11111",""+response.body().getResponse().toString());

//                Log.i(TAG, "onResponse: " + (response.raw().cacheResponse() != null ? "Cache" : "Network"));

                // Got data. Send it to adapter
                List<ResultAppoinment> results = fetchResults(response);
                Log.d("11111",""+results.toString());

                progressBar.setVisibility(View.GONE);
                mAdapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                t.printStackTrace();
                showErrorView(t);
            }
        });
    }

    /**
     * @param response extracts List<{@link Result>} from response
     * @return
     */
    private List<ResultAppoinment> fetchResults(Response<ResultList> response) {
        ResultList resultList = response.body();
        return resultList.getResponse();
    }
    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callApi().enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
//                Log.i(TAG, "onResponse: " + currentPage
//                        + (response.raw().cacheResponse() != null ? "Cache" : "Network"));

                Log.e("11111",""+response.body().getResponse().toString());
                mAdapter.removeLoadingFooter();
                isLoading = false;

                List<ResultAppoinment> results = fetchResults(response);
                mAdapter.addAll(results);

                if (currentPage != TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                t.printStackTrace();
                mAdapter.showRetry(true, fetchErrorMessage(t));
            }
        });
    }

    @Override
    public void retryPageLoad() {
        loadNextPage();
    }

    private Call<ResultList> callApi() {
        Log.e("111111",""+userId+" CurrentPAge "+currentPage);
        return apiService.getOpenedCarList("get_all_upcoming_appointment", 1,userId,"staff", currentPage);
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



