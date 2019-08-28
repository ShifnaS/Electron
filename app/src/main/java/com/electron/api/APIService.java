package com.electron.api;

import com.electron.model.ResponseAppoinmentData;
import com.electron.model.ResponseChangePassword;
import com.electron.model.ResponseDiscount;
import com.electron.model.ResponseExtra;
import com.electron.model.ResponseLogin;
import com.electron.model.ResponseMethod;
import com.electron.model.ResponseNotification;
import com.electron.model.ResponseProfile;
import com.electron.model.ResponseProfileUpdate;
import com.electron.model.ResponseService;
import com.electron.model.ResponseTimeSlot;
import com.electron.model.ResponseUnit;
import com.electron.model.ResultAppoinment;
import com.electron.model.ResultList;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {
  @FormUrlEncoded
  @POST("api/")
  Call<ResponseChangePassword> changePassword(@Field("action") String action, @Field("api_key") int api_key, @Field("user_id") int user_id, @Field("type") String type, @Field("old_password")  String old_password, @Field("new_password") String new_password, @Field("confirm_password") String confirm_password);

  @FormUrlEncoded
  @POST("api/")
  Call<ResponseProfileUpdate> updateProfile(@Field("action") String action, @Field("api_key") int api_key, @Field("type") String type, @Field("user_id") int user_id, @Field("fullname") String full_name, @Field("email") String email, @Field("phone") String phone, @Field("address") String address, @Field("city") String city, @Field("state") String state, @Field("zip") String zip, @Field("country") String country);

  @FormUrlEncoded
  @POST("api/")
  Call<ResponseProfile> getProfile(@Field("action") String action, @Field("api_key") int api_key,@Field("user_id") int paramInt,@Field("type") String type);
  


  @FormUrlEncoded
  @POST("api/")
  Call<ResponseLogin> login(@Field("email") String paramString1, @Field("password") String paramString2, @Field("action") String action, @Field("api_key") String api_key);

  @FormUrlEncoded
  @POST("api/")
  Call<ResultList> getOpenedCarList(@Field("action") String action, @Field("api_key") int api_key, @Field("user_id")int user_id,@Field("type") String type ,@Field("page") int page);

  @FormUrlEncoded
  @POST("api/")
  Call<ResponseService> getService(@Field("action") String action, @Field("api_key") String api_key);



  @FormUrlEncoded
  @POST("api/")
  Call<ResponseMethod> getMethod(@Field("action") String action, @Field("api_key") String api_key, @Field("service_id") int service_id);

  @FormUrlEncoded
  @POST("api/")
  Call<ResponseUnit> getUnit(@Field("action") String action, @Field("api_key") String api_key, @Field("service_id") int service_id,@Field("method_id") int method_id);
  @FormUrlEncoded
  @POST("api/")
  Call<ResponseExtra> getExtra(@Field("action") String action, @Field("api_key") String api_key, @Field("service_id") int service_id);

  @FormUrlEncoded
  @POST("api/")
  Call<ResponseDiscount> getDiscount(@Field("action") String action, @Field("api_key") String api_key);


  @FormUrlEncoded
  @POST("api/")
  Call<ResponseTimeSlot> getTimeSlot(@Field("action") String action, @Field("api_key") String api_key, @Field("selected_date") String selected_date, @Field("staff_id") int user_id);

  @FormUrlEncoded
  @POST("api/")
  Call<ResponseAppoinmentData> get_appointment_detail(@Field("action")String action, @Field("api_key")String api_key, @Field("order_id") String order_id);
}


/* Location:              C:\Users\pc\Downloads\student_project\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\info\apatrix\empiregarage\api\APIService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.0
 */