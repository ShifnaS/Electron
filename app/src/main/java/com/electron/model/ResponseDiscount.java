package com.electron.model;

import java.util.ArrayList;

public class ResponseDiscount {
    private int statuscode;

    private ArrayList<Discount> response;

    private String status;

    public int getStatuscode ()
    {
        return statuscode;
    }

    public void setStatuscode (int statuscode)
    {
        this.statuscode = statuscode;
    }

    public ArrayList<Discount>  getResponse ()
    {
        return response;
    }

    public void setResponse (ArrayList<Discount>  response)
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
