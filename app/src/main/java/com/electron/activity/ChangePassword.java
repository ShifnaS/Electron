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
import com.electron.model.ResponseChangePassword;
import com.electron.model.ResponseLogin;
import com.electron.utils.Constants;
import com.electron.utils.NetworkUtil;
import com.electron.utils.SharedPreferenceUtils;
import com.electron.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

  @BindView(R.id.input_password_current)
  EditText _input_password_current;
  @BindView(R.id.input_password)
  EditText _input_new_password_current;
  @BindView(R.id.btn_login)
  Button _loginButton;
  @BindView(R.id.input_password_retype)
  EditText _input_password_retype;
  private String auth;
  private int userid;
  private ProgressDialog progressDialog ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_change_password);
    progressDialog = new ProgressDialog(ChangePassword.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    ButterKnife.bind(this);
    _loginButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        userid= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_USER_ID);

        if(NetworkUtil.isOnline())
        {
          changepassword();


        }
        else
        {
          Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }


  public void changepassword() {
    progressDialog.setMessage("Updating...");
    progressDialog.show();
    if (!validate()) {
      onFailed("Login failed");
      return;
    }

    _loginButton.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(ChangePassword.this,
            R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Changing...");
    progressDialog.show();



    // TODO: Implement your own authentication logic here.

    new android.os.Handler().postDelayed(
            new Runnable() {
              public void run() {
                // On complete call either onLoginSuccess or onLoginFailed
                String current = _input_password_current.getText().toString();
                String new_password = _input_new_password_current.getText().toString();
                String retype = _input_new_password_current.getText().toString();

                APIService service = ApiModule.getAPIService();
                Call<ResponseChangePassword>call = service.changePassword("change_password",1,userid,"staff",current,new_password,retype );
                call.enqueue(new Callback<ResponseChangePassword>() {
                  @Override
                  public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                    progressDialog.dismiss();

                    try
                    {
                      if(response.body()!=null&&response.isSuccessful())
                      {
                        if(response.body().getStatuscode()==200)
                        {
                          Toast.makeText(ChangePassword.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                          onSuccess();
                        }
                        else
                        {
                          onFailed(response.body().getResponse());
                        }
                      }
                      else
                      {
                        onFailed("No response from the server");
                      }


                    }
                    catch (Exception e)
                    {
                      e.printStackTrace();
                      Log.e("Exception ",e.getMessage());


                    }

                  }

                  @Override
                  public void onFailure(Call<ResponseChangePassword> call, Throwable t) {
                    progressDialog.dismiss();


                    Log.e("MyTag", "requestFailed", t);
                    Log.e("Failure ",t.getMessage());

                  }
                });




              }
            }, 3000);
  }

  public void onSuccess() {
    _loginButton.setEnabled(true);
    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
    finish();
  }

  public void onFailed(String msg) {
    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    _loginButton.setEnabled(true);
  }
  public boolean validate() {
    boolean valid = true;

    String current = _input_password_current.getText().toString().trim();
    String new_password = _input_new_password_current.getText().toString().trim();
    String retype = _input_password_retype.getText().toString().trim();

    if (current.isEmpty())
    {
      _input_new_password_current.setError("please enter the field");
      valid = false;
    }
    else
    {
      _input_new_password_current.setError(null);
    }


    if (new_password.isEmpty() || new_password.length() < 4 || new_password.length() > 10)
    {
      _input_new_password_current.setError("password must between 4 and 10 alphanumeric characters");
      valid = false;
    }
    else
    {
      _input_new_password_current.setError(null);
    }

    if (retype.isEmpty() )
    {
      _input_password_retype.setError("please enter the field");
      valid = false;
    }
    else
    {
      _input_password_retype.setError(null);
    }
    return valid;
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
  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, HomeActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
    super.onBackPressed();
  }
}
