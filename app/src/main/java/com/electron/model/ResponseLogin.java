package com.electron.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {
  @SerializedName("statuscode")
  private int statuscode;
  
  @SerializedName("response")
  private Result response;

  @SerializedName("status")
  private boolean status;

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getStatuscode() {
    return statuscode;
  }

  public void setStatuscode(int statuscode) {
    this.statuscode = statuscode;
  }

 public Result getResponse() {
    return response;
  }

  public void setResponse(Result response) {
    this.response = response;
  }


}


/* Location:              C:\Users\pc\Downloads\student_project\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\info\apatrix\empiregarage\model\ResponseLogin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.0
 */