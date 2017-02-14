package com.example.install.gameclub;
//blank fragment to be used with the item view
public class ScheduleContentFragment{
    private String name;
    private String description;
    private long startTime;
    private long endTime;



    public ScheduleContentFragment(String name , String description , long startTime , long endTime){
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
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
