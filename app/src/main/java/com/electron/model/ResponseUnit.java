package com.electron.model;

import java.util.ArrayList;

public class ResponseUnit {
    private int statuscode;

    private String no_of_dropdown;

    private ArrayList<Unit> response;

    private String status;

    public int getStatuscode ()
    {
        return statuscode;
    }

    public void setStatuscode (int statuscode)
    {
        this.statuscode = statuscode;
    }

    public String getNo_of_dropdown ()
    {
        return no_of_dropdown;
    }

    public void setNo_of_dropdown (String no_of_dropdown)
    {
        this.no_of_dropdown = no_of_dropdown;
    }

    public ArrayList<Unit> getResponse ()
    {
        return response;
    }

    public void setResponse ( ArrayList<Unit> response)
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
        return "ClassPojo [statuscode = "+statuscode+", no_of_dropdown = "+no_of_dropdown+", response = "+response+", status = "+status+"]";
    }
}
