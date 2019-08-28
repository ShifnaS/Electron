package com.electron.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.electron.api.APIService;
import com.electron.api.APIUrl;
import com.electron.fragment.AllTaskFragment;
import com.electron.fragment.DailyFragment;
import com.electron.utils.Constants;
import com.electron.utils.NetworkUtil;
import com.electron.utils.SharedPreferenceUtils;
import com.electron.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

  @BindView(R.id.service)
  TextView tv_service;
  @BindView(R.id.opened)
  TextView _opened;
  @BindView(R.id.requested)
  TextView _requested;

  @BindView(R.id.mymenu)
  ImageView menus;

  @BindView(R.id.toolbar)
  Toolbar topToolBar;


  private String service="";
  private int rollId,userId;
  private String token="";
  private FloatingActionButton fab;


  Retrofit retrofit;
  APIService apiService;
  Disposable disposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_home);
    setSupportActionBar(topToolBar);
    ButterKnife.bind(this);
    _opened.setOnClickListener(this);
    _requested.setOnClickListener(this);
    fab=findViewById(R.id.fab);

    menus.setOnClickListener(this);

    rollId= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_ROLE_ID);
    userId= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_USER_ID);
    //token="123456789";
    token= SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_AUTH_TOKEN);

    service="opened";
    _opened.setPaintFlags(_opened.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    _opened.setText("Today's Task");
    //  _opened.setImageResource(R.drawable.ic_opened_services_active);
    loadHomeFragment();

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // We are showing only toast message. However, you can do anything you need.
        /// Toast.makeText(getContext(), "You clicked Floating Action Button", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(), AppoinmentActivity.class);
        startActivity(i);
      }
    });

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    retrofit = new Retrofit.Builder()
            .baseUrl(APIUrl.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    apiService = retrofit.create(APIService.class);






  }

  private void loadHomeFragment() {
    DailyFragment dailyFragment = new DailyFragment();
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.output, dailyFragment);
    fragmentTransaction.commit();
  }
  @Override
  public void onClick(View v) {
    switch (v.getId()) {

      case R.id.opened:
        service="opened";
        topToolBar.setTitle("Today's Task");
        tv_service.setText("Today's Task");
        _requested.setPaintFlags(_opened.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));

        _opened.setPaintFlags(_opened.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        _opened.setText("Today's Task");
      //  _opened.setText(R.drawable.ic_opened_services_active);
        //_requested.setImageResource(R.drawable.ic_requested_inventory);

        if(NetworkUtil.isOnline())
        {
            //Toast.makeText(this, "hii", Toast.LENGTH_SHORT).show();

          DailyFragment requestedFragment = new DailyFragment();
          FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
          fragmentTransaction.replace(R.id.output, requestedFragment);
          fragmentTransaction.commit();
        }
        else
        {
          Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }

        break;

      case R.id.requested:
        // do your code
        service="requested";
        topToolBar.setTitle("All Task");
        tv_service.setText("All Task");
        _opened.setPaintFlags(_opened.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));

        _requested.setPaintFlags(_opened.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        _requested.setText("All Task");
        if(NetworkUtil.isOnline())
        {
          AllTaskFragment requestedFragment = new AllTaskFragment();
          FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
          fragmentTransaction.replace(R.id.output, requestedFragment);
          fragmentTransaction.commit();
        }
        else
        {
          Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }

        break;


      case R.id.mymenu:
       // Toast.makeText(this, "fghfghfh", Toast.LENGTH_SHORT).show();
        setOption();
        break;
      default:
        break;
    }

  }

  private void setOption() {
    //Creating the instance of PopupMenu
    Context wrapper = new ContextThemeWrapper(HomeActivity.this, R.style.PopUpTheme);

    PopupMenu popup = new PopupMenu(wrapper, menus);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      popup.setGravity(Gravity.END);
    }

    //Inflating the Popup using xml file
    popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

    //registering popup with OnMenuItemClickListener
    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
      public boolean onMenuItemClick(MenuItem item) {
        Intent i;
        // Toast.makeText(HomeActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getTitle().toString())
        {

          case "Profile" :
            i=new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(i);
            break;
          case "Change Password" :
            i=new Intent(getApplicationContext(),ChangePassword.class);
            startActivity(i);
            break;
          case "Logout" :
            // showBottomSheetDialog();
            String auth=SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_AUTH_PASSWORD);
            SharedPreferenceUtils.getInstance(getApplicationContext()).clear();
            SharedPreferenceUtils.getInstance(getApplicationContext()).setStringValue(Constants.KEY_AUTH_PASSWORD,auth);
            i=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            finish();
            break;
          default:
            break;
        }
        return true;
      }
    });

    popup.show();//showing popup menu
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
   // getMenuInflater().inflate(R.menu.menu, menu);

    return true;

  }

  @Override
  public void onRefresh() {

  }

  @Override
  protected void onResume() {
    super.onResume();


  }



  private void onError(Throwable throwable) {
    Toast.makeText(this, "OnError in Observable Timer",
            Toast.LENGTH_LONG).show();
  }




  private void handleError(Throwable t) {

    //Add your error here.
  }

  @Override
  protected void onPause() {
    super.onPause();

  }



}
