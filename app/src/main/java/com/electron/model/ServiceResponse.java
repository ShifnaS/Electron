package com.electron.model;

import com.google.gson.annotations.SerializedName;

public class ServiceResponse {
    private String image;

    private String color;

    private String description;

    private String id;

    private String position;

    private String title;

    private String status;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [image = " + image + ", color = " + color + ", description = " + description + ", id = " + id + ", position = " + position + ", title = " + title + ", status = " + status + "]";
    }

////////////////////////////////////////////response class for getmethod()//////////////////////////////////

}