package com.electron.api;

import com.electron.model.ResponseDiscount;
import com.electron.model.ResponseExtra;
import com.electron.model.ResponseLogin;
import com.electron.model.ResponseMethod;
import com.electron.model.ResponseNotification;
import com.electron.model.ResponseProfile;
import com.electron.model.ResponseService;
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
  @POST("Settings/change_password")
  Call<ResponseLogin> changePassword(@Field("new_password") String paramString1, @Field("user_id") int paramInt, @Header("Authtoken") String paramString2);


    @FormUrlEncoded
    @POST("Settings/notifications")
    Observable<ResponseNotification> getRandomNotification(@Field("userId") int userId, @Header("Authtoken") String token);






  @FormUrlEncoded
  @POST("Settings/user_profile")
  Call<ResponseProfile> getProfile(@Field("user_id") int paramInt, @Header("Authtoken") String paramString);
  
  @FormUrlEncoded
  @POST("Technician_services/requested_inventory_services")
  Call<ResultList> getRequestedCarList(@Field("roleId") int paramInt1, @Field("userId") int paramInt2, @Header("Authtoken") String paramString);
  


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
  @POST("Settings/user_editprofile")
  Call<ResponseProfile> updateProfile(@Field("user_id") int paramInt, @Field("email") String paramString1, @Field("full_name") String paramString2, @Header("Authtoken") String paramString3);

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
}


/* Location:              C:\Users\pc\Downloads\student_project\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\info\apatrix\empiregarage\api\APIService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.0
 */