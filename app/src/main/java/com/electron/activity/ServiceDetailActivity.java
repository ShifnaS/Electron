package com.electron.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.electron.R;
import com.electron.api.APIService;
import com.electron.api.ApiModule;
import com.electron.model.AppoinmentData;
import com.electron.model.ResponseAppoinmentData;
import com.electron.model.ResponseLogin;
import com.electron.model.Result;
import com.electron.utils.Constants;
import com.electron.utils.SharedPreferenceUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailActivity extends AppCompatActivity {

    private String order_id="";
    private TextView tv_client_name,tv_date,tv_time,tv_payment_method,tv_price,tv_service_name,tv_method_name,tv_unit_name,tv_addon;
    private EditText et_remark;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 1;
    private GestureOverlayView gestureOverlayView ;
    private ArrayList<AppoinmentData> appoinmentDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_service_detail);

        ///textviews
        tv_client_name=findViewById(R.id.client_name);
        tv_date=findViewById(R.id.date);
        tv_time=findViewById(R.id.time);
        tv_payment_method=findViewById(R.id.payment_method);
        tv_price=findViewById(R.id.price);
        tv_service_name=findViewById(R.id.service_name);
        tv_method_name=findViewById(R.id.method_name);
        tv_unit_name=findViewById(R.id.unit_name);
        tv_addon=findViewById(R.id.addon);

        //editText
        et_remark=findViewById(R.id.remark);

        //gestureOverlayView
        gestureOverlayView=findViewById(R.id.sign_pad);

        Intent i=getIntent();
        order_id=i.getStringExtra("order_id");
        get_appointment_detail();
       /// Toast.makeText(this, "order_id "+order_id, Toast.LENGTH_SHORT).show();
    }

    private void get_appointment_detail() {
        final ProgressDialog progressDialog = new ProgressDialog(ServiceDetailActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Changing...");
        progressDialog.show();
        APIService service = ApiModule.getAPIService();
        Call<ResponseAppoinmentData> call = service.get_appointment_detail("get_appointment_detail","1",order_id);
        call.enqueue(new Callback<ResponseAppoinmentData>() {
            @Override
            public void onResponse(Call<ResponseAppoinmentData> call, Response<ResponseAppoinmentData> response) {
                progressDialog.dismiss();

                try
                {
                    Log.e("response","111111 "+response.body().toString());

                    if(response.body()!=null&&response.isSuccessful())
                    {
                        Log.e("response","111111 "+response.body().getStatuscode());
                        if(response.body().getStatuscode()==200)
                        {

                            appoinmentDataList=response.body().getResponse();
                            AppoinmentData data=appoinmentDataList.get(0);
                            tv_client_name.setText(data.getClient_name());
                            tv_date.setText(data.getStart_date());
                            tv_time.setText(data.getStart_time());
                            tv_payment_method.setText(data.getPayment_type());
                            tv_price.setText(data.getBooking_price());
                            tv_service_name.setText(data.getService_title());


                            tv_method_name.setText(""+data.getMethod_title());
                            tv_unit_name.setText(""+data.getUnit_title());
                            tv_addon.setText(""+data.getAddons_title());

                        }
                        else
                        {
                            Toast.makeText(ServiceDetailActivity.this, "Invalid Response", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(ServiceDetailActivity.this, "No Response", Toast.LENGTH_SHORT).show();
                    }


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());
                    //_loginButton.setEnabled(true);
                    //progressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<ResponseAppoinmentData> call, Throwable t) {
                //       progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();
               // _loginButton.setEnabled(true);
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });

    }
}
