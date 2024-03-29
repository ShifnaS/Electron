package com.electron.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseProfile {
  private int statuscode;

  private ArrayList<Profile> response;

  private String status;

  public int getStatuscode ()
  {
    return statuscode;
  }

  public void setStatuscode (int statuscode)
  {
    this.statuscode = statuscode;
  }

  public ArrayList<Profile> getProfile ()
  {
    return response;
  }

  public void setProfile (ArrayList<Profile> response)
  {
    this.response = response;
  }

  public String getStatus ()
  {
    return status;
  }

  public void setStatus (String status)
  {
    this.status = status;
  }

  @Override
  public String toString()
  {
    return "ClassPojo [statuscode = "+statuscode+", profile = "+response+", status = "+status+"]";
  }
}


/* Location:              C:\Users\pc\Downloads\student_project\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\info\apatrix\empiregarage\model\ResponseProfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.0
 */