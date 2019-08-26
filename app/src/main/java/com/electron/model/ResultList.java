package com.electron.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultList {
  @SerializedName("statuscode")
  private int statusCode;
  
  @SerializedName("response")
  private ArrayList<ResultAppoinment> response;

  @SerializedName("status")
  private String status;

  public int getstatusCode() { return this.statusCode; }

  public ArrayList<ResultAppoinment> getResponse() { return this.response; }
  
  public String getStatus() { return this.status; }
  
  public void setstatusCode(int statusCode) { this.statusCode = statusCode; }
  
  public void setResponse(ArrayList<ResultAppoinment> paramArrayList) { this.response = paramArrayList; }
  
  public void setStatus(String paramString) { this.status = paramString; }


  @Override
  public String toString() {
    return "ResultList{" +
            "statusCode=" + statusCode +
            ", response=" + response +
            ", status='" + status + '\'' +
            '}';
  }
}


/* Location:              C:\Users\pc\Downloads\student_project\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\info\apatrix\empiregarage\model\ResultList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.0
 */