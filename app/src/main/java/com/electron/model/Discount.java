package com.electron.model;

public class Discount {
    private String d_type;

    private String discount_typename;

    private String stripe_plan_id;

    private String rates;

    private String days;

    private String booking_count;

    private String id;

    private String labels;

    private String status;

    public String getD_type ()
    {
        return d_type;
    }

    public void setD_type (String d_type)
    {
        this.d_type = d_type;
    }

    public String getDiscount_typename ()
    {
        return discount_typename;
    }

    public void setDiscount_typename (String discount_typename)
    {
        this.discount_typename = discount_typename;
    }

    public String getStripe_plan_id ()
    {
        return stripe_plan_id;
    }

    public void setStripe_plan_id (String stripe_plan_id)
    {
        this.stripe_plan_id = stripe_plan_id;
    }

    public String getRates ()
    {
        return rates;
    }

    public void setRates (String rates)
    {
        this.rates = rates;
    }

    public String getDays ()
    {
        return days;
    }

    public void setDays (String days)
    {
        this.days = days;
    }

    public String getBooking_count ()
    {
        return booking_count;
    }

    public void setBooking_count (String booking_count)
    {
        this.booking_count = booking_count;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLabels ()
    {
        return labels;
    }

    public void setLabels (String labels)
    {
        this.labels = labels;
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
        return "ClassPojo [d_type = "+d_type+", discount_typename = "+discount_typename+", stripe_plan_id = "+stripe_plan_id+", rates = "+rates+", days = "+days+", booking_count = "+booking_count+", id = "+id+", labels = "+labels+", status = "+status+"]";
    }
}
