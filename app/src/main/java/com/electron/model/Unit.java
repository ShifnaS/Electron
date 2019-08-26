package com.electron.model;

import java.util.ArrayList;

public class Unit {
    private String services_id;

    private String uduration;

    private String units_title;

    private String unit_symbol;

    private String limit_title;

    private String half_section;

    private String minlimit;

    private String maxlimit;

    private ArrayList<Rate> rate_and_qty;

    private String base_price;

    private String name;

    private String id;

    private String position;

    private String methods_id;

    private String status;

    public String getServices_id ()
    {
        return services_id;
    }

    public void setServices_id (String services_id)
    {
        this.services_id = services_id;
    }

    public String getUduration ()
    {
        return uduration;
    }

    public void setUduration (String uduration)
    {
        this.uduration = uduration;
    }

    public String getUnits_title ()
    {
        return units_title;
    }

    public void setUnits_title (String units_title)
    {
        this.units_title = units_title;
    }

    public String getUnit_symbol ()
    {
        return unit_symbol;
    }

    public void setUnit_symbol (String unit_symbol)
    {
        this.unit_symbol = unit_symbol;
    }

    public String getLimit_title ()
    {
        return limit_title;
    }

    public void setLimit_title (String limit_title)
    {
        this.limit_title = limit_title;
    }

    public String getHalf_section ()
    {
        return half_section;
    }

    public void setHalf_section (String half_section)
    {
        this.half_section = half_section;
    }

    public String getMinlimit ()
    {
        return minlimit;
    }

    public void setMinlimit (String minlimit)
    {
        this.minlimit = minlimit;
    }

    public String getMaxlimit ()
    {
        return maxlimit;
    }

    public void setMaxlimit (String maxlimit)
    {
        this.maxlimit = maxlimit;
    }

    public ArrayList<Rate> getRate_and_qty ()
    {
        return rate_and_qty;
    }

    public void setRate_and_qty (ArrayList<Rate> rate_and_qty)
    {
        this.rate_and_qty = rate_and_qty;
    }

    public String getBase_price ()
    {
        return base_price;
    }

    public void setBase_price (String base_price)
    {
        this.base_price = base_price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPosition ()
    {
        return position;
    }

    public void setPosition (String position)
    {
        this.position = position;
    }

    public String getMethods_id ()
    {
        return methods_id;
    }

    public void setMethods_id (String methods_id)
    {
        this.methods_id = methods_id;
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
        return "ClassPojo [services_id = "+services_id+", uduration = "+uduration+", units_title = "+units_title+", unit_symbol = "+unit_symbol+", limit_title = "+limit_title+", half_section = "+half_section+", minlimit = "+minlimit+", maxlimit = "+maxlimit+", rate_and_qty = "+rate_and_qty+", base_price = "+base_price+", name = "+name+", id = "+id+", position = "+position+", methods_id = "+methods_id+", status = "+status+"]";
    }
}
