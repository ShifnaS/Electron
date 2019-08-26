package com.electron.model;

public class Rate {
    private String rate;

    private String qty;

    public String getRate ()
    {
        return rate;
    }

    public void setRate (String rate)
    {
        this.rate = rate;
    }

    public String getQty ()
    {
        return qty;
    }

    public void setQty (String qty)
    {
        this.qty = qty;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rate = "+rate+", qty = "+qty+"]";
    }
}
