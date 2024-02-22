package com.example.careminder.view;

public class NurseItemView {
    private Boolean type;
    private Boolean color;
    private String title;
    private String description;
    private String time;

    public NurseItemView() {
    }

    public NurseItemView(Boolean type, Boolean color, String title, String description, String time) {
        this.type = type;
        this.color = color;
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public Boolean getType() {
        return type;
    }
    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getColor() {
        return color;
    }
    public void setColor(Boolean color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
