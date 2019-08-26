package com.electron.model;

public class Extra {
    private String multipleqty;

    private String image;

    private String aduration;

    private String addon_service_name;

    private String predefine_image_title;

    private String predefine_image;

    private String service_id;

    private String base_price;

    private String name;

    private String checked;

    private String id;

    private String position;

    private String maxqty;

    private String status;

    public String getMultipleqty ()
    {
        return multipleqty;
    }

    public void setMultipleqty (String multipleqty)
    {
        this.multipleqty = multipleqty;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getAduration ()
    {
        return aduration;
    }

    public void setAduration (String aduration)
    {
        this.aduration = aduration;
    }

    public String getAddon_service_name ()
    {
        return addon_service_name;
    }

    public void setAddon_service_name (String addon_service_name)
    {
        this.addon_service_name = addon_service_name;
    }

    public String getPredefine_image_title ()
    {
        return predefine_image_title;
    }

    public void setPredefine_image_title (String predefine_image_title)
    {
        this.predefine_image_title = predefine_image_title;
    }

    public String getPredefine_image ()
    {
        return predefine_image;
    }

    public void setPredefine_image (String predefine_image)
    {
        this.predefine_image = predefine_image;
    }

    public String getService_id ()
    {
        return service_id;
    }

    public void setService_id (String service_id)
    {
        this.service_id = service_id;
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

    public String getChecked ()
    {
        return checked;
    }

    public void setChecked (String checked)
    {
        this.checked = checked;
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

    public String getMaxqty ()
    {
        return maxqty;
    }

    public void setMaxqty (String maxqty)
    {
        this.maxqty = maxqty;
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
        return "ClassPojo [multipleqty = "+multipleqty+", image = "+image+", aduration = "+aduration+", addon_service_name = "+addon_service_name+", predefine_image_title = "+predefine_image_title+", predefine_image = "+predefine_image+", service_id = "+service_id+", base_price = "+base_price+", name = "+name+", checked = "+checked+", id = "+id+", position = "+position+", maxqty = "+maxqty+", status = "+status+"]";
    }
}
