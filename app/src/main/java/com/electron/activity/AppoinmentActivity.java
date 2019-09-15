package com.electron.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.electron.R;
import com.electron.adapter.RecyclerAdapterMethod;
import com.electron.adapter.RecyclerAdapterTimeSlot;
import com.electron.adapter.RecyclerAdapterUnit;
import com.electron.adapter.RecyclerTouchListener;
import com.electron.adapter.RecyclerViewAdapter;
import com.electron.adapter.RecyclerViewAdapterDiscount;
import com.electron.adapter.RecyclerViewAdapterExtra;
import com.electron.api.APIService;
import com.electron.api.ApiModule;
import com.electron.model.Discount;
import com.electron.model.Extra;
import com.electron.model.Method;
import com.electron.model.ResponseDiscount;
import com.electron.model.ResponseExtra;
import com.electron.model.ResponseMethod;
import com.electron.model.ResponseService;
import com.electron.model.ResponseTimeSlot;
import com.electron.model.ResponseUnit;
import com.electron.model.ServiceResponse;
import com.electron.model.Unit;
import com.electron.utils.Constants;
import com.electron.utils.SharedPreferenceUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppoinmentActivity extends AppCompatActivity {
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerAdapterMethod recyclerViewAdaptermethod;
    private RecyclerAdapterUnit recyclerViewAdapterunit;
    private RecyclerViewAdapterExtra recyclerViewAdapterextra;
    private RecyclerViewAdapterDiscount recyclerViewAdapterdiscount;
    private RecyclerAdapterTimeSlot recyclerAdapterTimeSlot;
    private CalendarView simpleCalendarView;
    private Button bt_appoinment;

    private LinearLayoutManager linearLayoutManager,linearLayoutManagerMethod,linearLayoutManagerUnit;
    private RecyclerView recyclerView,recyclerViewMethod,recyclerViewUnit,recyclerViewExtra,recyclerViewDiscount,recyclerViewTimeSlot;
    private GridLayoutManager layoutManagerExtra,layoutManagerDiscount,layoutManagerTimeSlot;
    private TextView dateTime;

    private  ArrayList<ServiceResponse> serviceResponses=new ArrayList<>();
    private ArrayList<Method> methodResponses=new ArrayList<>();
    private  ArrayList<Unit> unitResponses=new ArrayList<>();
    private ArrayList<Extra> extraResponses=new ArrayList<>();
    private ArrayList<Discount> discountResposes=new ArrayList<>();
    private String[] timeSlot;
    private int userId;
    private String date="";
    private Button bt_newUser,bt_EUser;

    private LinearLayout linearLayoutService,linearLayoutCalenderview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);
        recyclerView=findViewById(R.id.service);
        recyclerViewMethod=findViewById(R.id.method);
        recyclerViewUnit=findViewById(R.id.unit);
        recyclerViewExtra=findViewById(R.id.extra);
        recyclerViewDiscount=findViewById(R.id.discount);
        simpleCalendarView=findViewById(R.id.calender);
        recyclerViewTimeSlot=findViewById(R.id.timeSlot);
        dateTime=findViewById(R.id.date);
        bt_appoinment=findViewById(R.id.appoinment);
        bt_EUser=findViewById(R.id.EUser);
        bt_newUser=findViewById(R.id.newUser);


        bt_newUser.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                bt_newUser.setBackground(getResources().getDrawable(R.drawable.textback));
                bt_EUser.setBackground(getResources().getDrawable(R.drawable.textbacktwo));
                bt_EUser.setTextColor(getResources().getColor(R.color.colorPrimary));
                bt_newUser.setTextColor(getResources().getColor(R.color.white));
            }
        });
        bt_EUser.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                bt_EUser.setBackground(getResources().getDrawable(R.drawable.textback));
                bt_newUser.setBackground(getResources().getDrawable(R.drawable.textbacktwo));
                bt_EUser.setTextColor(getResources().getColor(R.color.white));
                bt_newUser.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });


        linearLayoutService=findViewById(R.id.layoutService);
        linearLayoutCalenderview=findViewById(R.id.calenderview);

        userId= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_USER_ID);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManagerMethod = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMethod.setLayoutManager(linearLayoutManagerMethod);
        recyclerViewMethod.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManagerUnit= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewUnit.setLayoutManager(linearLayoutManagerUnit);
        recyclerViewUnit.setItemAnimator(new DefaultItemAnimator());

        layoutManagerExtra= new GridLayoutManager(getApplicationContext(),3,LinearLayoutManager.VERTICAL,false);
        recyclerViewExtra.setLayoutManager(layoutManagerExtra);
        recyclerViewExtra.setItemAnimator(new DefaultItemAnimator());

        layoutManagerDiscount= new GridLayoutManager(getApplicationContext(),3,LinearLayoutManager.VERTICAL,false);
        recyclerViewDiscount.setLayoutManager(layoutManagerDiscount);
        recyclerViewDiscount.setItemAnimator(new DefaultItemAnimator());

        layoutManagerTimeSlot= new GridLayoutManager(getApplicationContext(),3,LinearLayoutManager.VERTICAL,false);
        recyclerViewTimeSlot.setLayoutManager(layoutManagerTimeSlot);
        recyclerViewTimeSlot.setItemAnimator(new DefaultItemAnimator());

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int m=month+1;
              //  Toast.makeText(AppoinmentActivity.this, "DATE "+ year+"-"+month+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
                date=year+"-0"+m+"-0"+dayOfMonth;
                Log.e("1111111", ""+date);
                getTimeSlot();
            }
        });


        getService();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                public void onClick(View param1View, int param1Int) {
                    ServiceResponse service= serviceResponses.get(param1Int);
                    Toast.makeText(AppoinmentActivity.this, "hiii "+service.getId(), Toast.LENGTH_SHORT).show();
                    linearLayoutService.setVisibility(View.VISIBLE);
                    int service_id=Integer.parseInt(service.getId());
                    ///  getMethods();

                    getMethods(service_id);

                }

            public void onLongClick(View param1View, int param1Int) {}
        }));

        recyclerViewMethod.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewMethod, new RecyclerTouchListener.ClickListener() {
            public void onClick(View param1View, int param1Int) {
                Method method= methodResponses.get(param1Int);
                Toast.makeText(AppoinmentActivity.this, "hiii "+method.getId_method(), Toast.LENGTH_SHORT).show();
                recyclerViewUnit.setVisibility(View.VISIBLE);
                int method_id=Integer.parseInt(method.getId_method());
                int service_id=Integer.parseInt(method.getService_id());
                getExtra(service_id);
                getOffer();
                getUnit(method_id,service_id);
                linearLayoutCalenderview.setVisibility(View.VISIBLE);
                getCurrentDate();
                getTimeSlot();

            }

            public void onLongClick(View param1View, int param1Int) {}
        }));

        recyclerViewUnit.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewUnit, new RecyclerTouchListener.ClickListener() {
            public void onClick(View param1View, int param1Int) {
                Unit unit= unitResponses.get(param1Int);
                Toast.makeText(AppoinmentActivity.this, "hiii "+unit.getId(), Toast.LENGTH_SHORT).show();
                linearLayoutService.setVisibility(View.VISIBLE);

                int service_id=Integer.parseInt(unit.getServices_id());


            }

            public void onLongClick(View param1View, int param1Int) {}
        }));

        recyclerViewTimeSlot.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewTimeSlot, new RecyclerTouchListener.ClickListener() {
            public void onClick(View param1View, int param1Int) {
                String time= timeSlot[param1Int];
                Toast.makeText(AppoinmentActivity.this, "time selected "+time, Toast.LENGTH_SHORT).show();
                linearLayoutService.setVisibility(View.VISIBLE);

                dateTime.setText("Appoinment Date "+date+" "+time);
                bt_appoinment.setVisibility(View.VISIBLE);


            }

            public void onLongClick(View param1View, int param1Int) {}
        }));

    }
    private void getTimeSlot() {
        final ProgressDialog progressDialog = new ProgressDialog(AppoinmentActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();

        APIService service = ApiModule.getAPIService();
        Call<ResponseTimeSlot> call = service.getTimeSlot("get_slots","1",date,userId);
        call.enqueue(new Callback<ResponseTimeSlot>() {
            @Override
            public void onResponse(Call<ResponseTimeSlot> call, Response<ResponseTimeSlot> response) {
                progressDialog.dismiss();
                try
                {
                    Log.e("response","111114 "+response.body().toString());

                    if(response.body()!=null&&response.isSuccessful())
                    {

                        if(response.body().getStatuscode()==200)
                        {
                            timeSlot=response.body().getResponse();
                            recyclerAdapterTimeSlot= new RecyclerAdapterTimeSlot(timeSlot, getApplicationContext());
                            recyclerViewTimeSlot.setAdapter(recyclerAdapterTimeSlot);
                        }
                        else
                        {
                            onFailed("None of time slot available please check another dates");
                        }
                    }
                    else
                    {
                        onFailed("No Response");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseTimeSlot> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });

    }
    private void getExtra(int id) {
        final ProgressDialog progressDialog = new ProgressDialog(AppoinmentActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();
        APIService service = ApiModule.getAPIService();
        Call<ResponseExtra> call = service.getExtra("get_addons_of_selected_service","1",id);
        call.enqueue(new Callback<ResponseExtra>() {
            @Override
            public void onResponse(Call<ResponseExtra> call, Response<ResponseExtra> response) {
                progressDialog.dismiss();
                try
                {
                    Log.e("response","111114 "+response.body().toString());
                    if(response.body()!=null&&response.isSuccessful())
                    {

                        if(response.body().getStatuscode()==200)
                        {
                            extraResponses=response.body().getResponse();
                            recyclerViewAdapterextra= new RecyclerViewAdapterExtra(extraResponses, getApplicationContext());
                            recyclerViewExtra.setAdapter(recyclerViewAdapterextra);
                        }
                        else
                        {
                            onFailed("Failed");
                        }
                    }
                    else
                    {
                        onFailed("No Response");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseExtra> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });

    }

    private void getUnit(int method_id, int service_id) {
        final ProgressDialog progressDialog = new ProgressDialog(AppoinmentActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();
        APIService service = ApiModule.getAPIService();
        Call<ResponseUnit> call = service.getUnit("get_units_of_selected_method","1",service_id,method_id);
        call.enqueue(new Callback<ResponseUnit>() {
            @Override
            public void onResponse(Call<ResponseUnit> call, Response<ResponseUnit> response) {
                progressDialog.dismiss();
                try
                {
                    Log.e("response","111114 "+response.body().toString());
                    if(response.body()!=null&&response.isSuccessful())
                    {

                        if(response.body().getStatuscode()==200)
                        {
                            unitResponses=response.body().getResponse();
                            recyclerViewAdapterunit = new RecyclerAdapterUnit(unitResponses, getApplicationContext());
                            recyclerViewUnit.setAdapter(recyclerViewAdapterunit);
                        }
                        else
                        {
                            onFailed("Failed");
                        }
                    }
                    else
                    {
                        onFailed("No Response");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseUnit> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });
    }



    public void getService() {

        final ProgressDialog progressDialog = new ProgressDialog(AppoinmentActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();

        APIService service = ApiModule.getAPIService();
        Call<ResponseService> call = service.getService("get_all_services","1");
        call.enqueue(new Callback<ResponseService>() {
            @Override
            public void onResponse(Call<ResponseService> call, Response<ResponseService> response) {

                try
                {
                    if(response.body()!=null&&response.isSuccessful())
                    {
                        if(response.body().getStatuscode()==200)
                        {
                            serviceResponses=response.body().getResponse();
                            recyclerViewAdapter = new RecyclerViewAdapter(0,serviceResponses, getApplicationContext());
                            recyclerView.setAdapter(recyclerViewAdapter);
                            progressDialog.dismiss();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            onFailed("Failed");
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();
                        onFailed("No Response");
                    }
                }
                catch (Exception e)
                {
                    progressDialog.dismiss();
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseService> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });
    }
    public void getMethods(int id) {
        final ProgressDialog progressDialog = new ProgressDialog(AppoinmentActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();

        APIService service = ApiModule.getAPIService();
        Call<ResponseMethod> call = service.getMethod("get_methods_of_selected_service","1",id);
        call.enqueue(new Callback<ResponseMethod>() {
            @Override
            public void onResponse(Call<ResponseMethod> call, Response<ResponseMethod> response) {

                try
                {
                    Log.e("response","111114 "+response.body().toString());
                    if(response.body()!=null&&response.isSuccessful())
                    {

                        if(response.body().getStatuscode()==200)
                        {

                            methodResponses=response.body().getResponse();
                            recyclerViewAdaptermethod = new RecyclerAdapterMethod(methodResponses, getApplicationContext());
                            recyclerViewMethod.setAdapter(recyclerViewAdaptermethod);
                            progressDialog.dismiss();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            onFailed("Failed");
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();
                        onFailed("No Response");
                    }
                }
                catch (Exception e)
                {
                    progressDialog.dismiss();
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseMethod> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });
    }

    public void getOffer() {
        final ProgressDialog progressDialog = new ProgressDialog(AppoinmentActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();
        APIService service = ApiModule.getAPIService();
        Call<ResponseDiscount> call = service.getDiscount("get_all_frequently_discount","1");
        call.enqueue(new Callback<ResponseDiscount>() {
            @Override
            public void onResponse(Call<ResponseDiscount> call, Response<ResponseDiscount> response) {
                progressDialog.dismiss();
                try
                {
                    //  Log.e("response","111111 "+response.body().toString());
                    if(response.body()!=null&&response.isSuccessful())
                    {
                        //   Log.e("response","111112"+response.body().toString());
                        //  Log.e("response","111111 "+response.body().getStatuscode());
                        if(response.body().getStatuscode()==200)
                        {
                            discountResposes=response.body().getResponse();
                            recyclerViewAdapterdiscount = new RecyclerViewAdapterDiscount(discountResposes, getApplicationContext());
                            recyclerViewDiscount.setAdapter(recyclerViewAdapterdiscount);
                        }
                        else
                        {
                            onFailed("Failed");
                        }
                    }
                    else
                    {
                        onFailed("No Response");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseDiscount> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });
    }


    public void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        date= mdformat.format(calendar.getTime());
        Log.e("1111111","CurrentDate "+date);
    }

    private void onFailed(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
