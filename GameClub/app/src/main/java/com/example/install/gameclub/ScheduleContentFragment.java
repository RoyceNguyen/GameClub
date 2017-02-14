package com.example.install.gameclub;
//blank fragment to be used with the item view
public class ScheduleContentFragment{
    private String name;
    private String description;
    private String calendar;



    public ScheduleContentFragment(String name , String description , String calendar){
        this.name = name;
        this.description = description;
        this.calendar = calendar

    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
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
