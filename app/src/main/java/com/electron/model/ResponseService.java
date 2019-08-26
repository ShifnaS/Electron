package com.electron.model;


import java.util.ArrayList;

public class ResponseService
{
    private int statuscode;

    private ArrayList<ServiceResponse> response;

    private String status;

    public int getStatuscode ()
    {
        return statuscode;
    }

    public void setStatuscode (int statuscode)
    {
        this.statuscode = statuscode;
    }

    public  ArrayList<ServiceResponse>  getResponse ()
    {
        return response;
    }

    public void setResponse ( ArrayList<ServiceResponse>  response)
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

