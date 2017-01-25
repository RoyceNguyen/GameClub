package com.example.install.gameclub;

public class ScheduleContentFragment{
    private String name;
    private String description;

    public ScheduleContentFragment(String name , String description ){
        this.name = name;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return getName();
    }
}
