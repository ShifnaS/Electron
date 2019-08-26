package com.electron.model;

import java.util.ArrayList;

public class ResponseMethod {


    private int statuscode;

    private ArrayList<Method> response;

    private String status;

    public int getStatuscode ()
    {
        return statuscode;
    }

    public void setStatuscode (int statuscode)
    {
        this.statuscode = statuscode;
    }

    public  ArrayList<Method>  getResponse ()
    {
        return response;
    }

    public void setResponse ( ArrayList<Method>  response)
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
