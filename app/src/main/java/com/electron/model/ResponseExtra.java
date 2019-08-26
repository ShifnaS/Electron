package com.electron.model;

import java.util.ArrayList;

public class ResponseExtra {
    private int statuscode;

    private ArrayList<Extra> response;

    private String status;

    public int getStatuscode ()
    {
        return statuscode;
    }

    public void setStatuscode (int statuscode)
    {
        this.statuscode = statuscode;
    }

    public ArrayList<Extra>  getResponse ()
    {
        return response;
    }

    public void setResponse (ArrayList<Extra>  response)
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
        return "ClassPojo [statuscode = "+statuscode+", response = "+response+", status = "+status+"]";
    }
}
