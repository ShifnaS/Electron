package com.electron.model;

public class User {
    private String zip;

    private String p_status;

    private String user_email;

    private String address;

    private String notes;

    private String city;

    private String last_name;

    private String usertype;

    private String contact_status;

    private String user_pwd;

    private String phone;

    private String cus_dt;

    private String vc_status;

    private String id;

    private String state;

    private String first_name;

    private String stripe_id;

    private String status;

    public String getZip ()
    {
        return zip;
    }

    public void setZip (String zip)
    {
        this.zip = zip;
    }

    public String getP_status ()
    {
        return p_status;
    }

    public void setP_status (String p_status)
    {
        this.p_status = p_status;
    }

    public String getUser_email ()
    {
        return user_email;
    }

    public void setUser_email (String user_email)
    {
        this.user_email = user_email;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getNotes ()
    {
        return notes;
    }

    public void setNotes (String notes)
    {
        this.notes = notes;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getUsertype ()
    {
        return usertype;
    }

    public void setUsertype (String usertype)
    {
        this.usertype = usertype;
    }

    public String getContact_status ()
    {
        return contact_status;
    }

    public void setContact_status (String contact_status)
    {
        this.contact_status = contact_status;
    }

    public String getUser_pwd ()
    {
        return user_pwd;
    }

    public void setUser_pwd (String user_pwd)
    {
        this.user_pwd = user_pwd;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getCus_dt ()
    {
        return cus_dt;
    }

    public void setCus_dt (String cus_dt)
    {
        this.cus_dt = cus_dt;
    }

    public String getVc_status ()
    {
        return vc_status;
    }

    public void setVc_status (String vc_status)
    {
        this.vc_status = vc_status;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getStripe_id ()
    {
        return stripe_id;
    }

    public void setStripe_id (String stripe_id)
    {
        this.stripe_id = stripe_id;
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
        return "ClassPojo [zip = "+zip+", p_status = "+p_status+", user_email = "+user_email+", address = "+address+", notes = "+notes+", city = "+city+", last_name = "+last_name+", usertype = "+usertype+", contact_status = "+contact_status+", user_pwd = "+user_pwd+", phone = "+phone+", cus_dt = "+cus_dt+", vc_status = "+vc_status+", id = "+id+", state = "+state+", first_name = "+first_name+", stripe_id = "+stripe_id+", status = "+status+"]";
    }
}
			
