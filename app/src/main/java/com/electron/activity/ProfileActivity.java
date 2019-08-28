package com.electron.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.electron.api.APIService;
import com.electron.api.ApiModule;
import com.electron.model.Profile;
import com.electron.model.ResponseProfile;
import com.electron.model.ResponseProfileUpdate;
import com.electron.utils.Constants;
import com.electron.utils.NetworkUtil;
import com.electron.utils.SharedPreferenceUtils;
import com.electron.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.name)
    EditText et_name;
    @BindView(R.id.email)
    EditText et_email;
    @BindView(R.id.role)
    EditText et_role;
    @BindView(R.id.phone)
    EditText et_phone;
    @BindView(R.id.address)
    EditText et_address;
    @BindView(R.id.city)
    EditText et_city;
    @BindView(R.id.state)
    EditText et_state;
    @BindView(R.id.zipcode)
    EditText et_zip;
    @BindView(R.id.country)
    EditText et_country;

    private int userid;

    private String email = "",fullname = "",role="",phone="",address="",city="",state="",zip="",country="";
    private ProgressDialog progressDialog ;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_profile);

    userid= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_USER_ID);
    ButterKnife.bind(this);
      Toolbar toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      progressDialog = new ProgressDialog(ProfileActivity.this, R.style.AppTheme_Dark_Dialog);
      progressDialog.setIndeterminate(true);

      if(NetworkUtil.isOnline())
    {
        getData();
      _loginButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View param1View) {
          email = et_email.getText().toString();
          fullname = et_name.getText().toString();
          role=et_role.getText().toString();
          phone=et_phone.getText().toString();
          address=et_address.getText().toString();
          city=et_city.getText().toString();
          state=et_state.getText().toString();
          zip=et_zip.getText().toString();
          country=et_country.getText().toString();
          submitData();
        }
      });

    }
    else
    {
      Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
    }
  }



  private void submitData() {

    progressDialog.setMessage("Updating...");
    progressDialog.show();
    ApiModule.getAPIService().updateProfile("profile_detail_update",1,role,userid, fullname, email,phone,address,city,state,zip,country)
    .enqueue(new Callback<ResponseProfileUpdate>() {
      public void onFailure(Call<ResponseProfileUpdate> param1Call, Throwable param1Throwable)
      {
        progressDialog.dismiss();
        Log.e("MyTag", "requestFailed", param1Throwable);
        Log.e("Failure ", param1Throwable.getMessage());
      }

      public void onResponse(Call<ResponseProfileUpdate> param1Call, Response<ResponseProfileUpdate> response)
      { 
        progressDialog.dismiss();
        try {
          if (response.body().getStatuscode()==200) {
          
            Toast.makeText(getApplicationContext(), ""+response.body().getProfile(), Toast.LENGTH_SHORT).show();
           
          }
          else
          {
            Toast.makeText(getApplicationContext(), ""+response.body().getProfile(), Toast.LENGTH_SHORT).show();

          }
          
        } catch (Exception e) {
          e.printStackTrace();
          Log.e("Exception ", e.getMessage());
        }  }
    });
  }
  
  

   private void getData() {

       progressDialog.setMessage("Fetching...");
       progressDialog.show();
        APIService service = ApiModule.getAPIService();
        Call<ResponseProfile> call = service.getProfile("get_profile_detail",1,userid,"staff");
        call.enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {

                try
                {
                    Log.e("1111","Status code"+response.body().getStatuscode());
                    if(response.body().getStatuscode()==200)
                    {
                        Profile profile = response.body().getProfile().get(0);
                        et_name.setText(profile.getFullname());
                        et_email.setText(profile.getUser_email());
                        et_role.setText(profile.getRole());
                        et_phone.setText(profile.getPhone());
                        et_address.setText(profile.getAddress());
                        et_city.setText(profile.getCity());
                        et_state.setText(profile.getState());
                        et_zip.setText(profile.getZip());
                        et_country.setText(profile.getCountry());
                        progressDialog.dismiss();

                    }
                    else
                    {
                        progressDialog.dismiss();

                        Toast.makeText(ProfileActivity.this, "Failed to Fetch data", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<ResponseProfile> call, Throwable t) {
                //       progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();

                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        Intent i=new Intent(getApplicationContext(), HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);

    }
}
