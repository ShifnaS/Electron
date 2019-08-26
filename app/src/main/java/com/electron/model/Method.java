package com.electron.model;

import com.google.gson.annotations.SerializedName;

public class Method {
    @SerializedName("id")
    private String id_method;
    @SerializedName("service_id")
    private String  service_id;
    @SerializedName("method_title")
    private String  method_title;
    @SerializedName("status")
    private String  status_method;
    @SerializedName("position")
    private String  position_method;
    @SerializedName("name")
    private String  name;

    public String getId_method() {
        return id_method;
    }

    public void setId_method(String id_method) {
        this.id_method = id_method;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getMethod_title() {
        return method_title;
    }

    public void setMethod_title(String method_title) {
        this.method_title = method_title;
    }

    public String getStatus_method() {
        return status_method;
    }

    public void setStatus_method(String status_method) {
        this.status_method = status_method;
    }

    public String getPosition_method() {
        return position_method;
    }

    public void setPosition_method(String position_method) {
        this.position_method = position_method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Method{" +
                "id_method='" + id_method + '\'' +
                ", service_id='" + service_id + '\'' +
                ", method_title='" + method_title + '\'' +
                ", status_method='" + status_method + '\'' +
                ", position_method='" + position_method + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
