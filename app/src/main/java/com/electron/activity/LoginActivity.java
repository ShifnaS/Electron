package com.electron.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.electron.api.APIService;
import com.electron.api.ApiModule;
import com.electron.model.ResponseLogin;
import com.electron.model.Result;
import com.electron.utils.Constants;
import com.electron.utils.NetworkUtil;
import com.electron.utils.SharedPreferenceUtils;
import com.electron.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
  @BindView(R.id.input_email)
  EditText _emailText;
  @BindView(R.id.input_password)
  EditText _passwordText;
  @BindView(R.id.btn_login)
  Button _loginButton;
  @BindView(R.id.link_signup)
  TextView _signupLink;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);

    _loginButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {


        if(NetworkUtil.isOnline())
        {
          login();


        }
        else
        {
          Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }
      }
    });

    _signupLink.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // Start the Signup activity
        Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
        startActivity(intent);
        finish();
      }
    });


  }

  public void login() {

    //  Log.d(TAG, "Login");
   //onLoginSuccess();

    if (!validate()) {
      onLoginFailed("Login failed");
      return;
    }

    _loginButton.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
            R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Authenticating...");
    progressDialog.show();



    // TODO: Implement your own authentication logic here.

    new android.os.Handler().postDelayed(
            new Runnable() {
              public void run() {
                // On complete call either onLoginSuccess or onLoginFailed
                String email = _emailText.getText().toString().trim();
                final String password = _passwordText.getText().toString().trim();
                APIService service = ApiModule.getAPIService();
                Call<ResponseLogin> call = service.login(email,password,"check_login","1");
                call.enqueue(new Callback<ResponseLogin>() {
                  @Override
                  public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    progressDialog.dismiss();

                    try
                    {
                     // Log.w("2.0 getResponse ",new Gson().toJson(response));
                      Log.e("response","111111 "+response.body().toString());

                      if(response.body()!=null&&response.isSuccessful())
                      {
                        Log.e("response","111112"+response.body().toString());
                      //  Headers headerList =response.headers();
                       // String authToken= headerList.get("Authtoken");
                        Log.e("response","111111 "+response.body().getStatuscode());
                        if(response.body().getStatuscode()==200)
                        {

                          Result obj=response.body().getResponse();
                          String userId=obj.getId();
                          String password=obj.getPassword();
                          int userid=Integer.parseInt(userId);
                          ///int password1=Integer.parseInt(password);
                          SharedPreferenceUtils.getInstance(getApplicationContext()).setIntValue(Constants.KEY_USER_ID,userid);
                          SharedPreferenceUtils.getInstance(getApplicationContext()).setStringValue(Constants.KEY_AUTH_PASSWORD,password);

                          onLoginSuccess();

                        }
                        else
                        {
                          onLoginFailed("Invalid User Name or Password");
                        }
                      }
                      else
                      {
                        onLoginFailed("Invalid User Name or Password");
                      }


                    }
                    catch (Exception e)
                    {
                      e.printStackTrace();
                      Log.e("Exception ",e.getMessage());

                      progressDialog.dismiss();

                    }

                  }

                  @Override
                  public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    //       progressBar.setVisibility(View.GONE);
                    progressDialog.dismiss();

                    Log.e("MyTag", "requestFailed", t);
                    Log.e("Failure ",t.getMessage());

                  }
                });




              }
            }, 3000);
  }

  @Override
  public void onBackPressed() {
    // Disable going back to the MainActivity
    moveTaskToBack(true);
  }

  public void onLoginSuccess() {
    _loginButton.setEnabled(true);
    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(intent);
    finish();
  }

  public void onLoginFailed(String msg) {
    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

    _loginButton.setEnabled(true);
  }

  public boolean validate() {
    boolean valid = true;

    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      _emailText.setError("enter a valid email address");
      valid = false;
    } else {
      _emailText.setError(null);
    }

    if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
      _passwordText.setError("between 4 and 10 alphanumeric characters");
      valid = false;
    } else {
      _passwordText.setError(null);
    }

    return valid;
  }

}
