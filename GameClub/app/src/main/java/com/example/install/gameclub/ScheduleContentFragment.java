package com.example.install.gameclub;
//blank fragment to be used with the item view
public class ScheduleContentFragment{
    private int id;
    private String name;
    private String description;
    //private long startTime;
    //private long endTime;
    private String url;



    public ScheduleContentFragment(String name , String description){
        this.name = name;
        this.description = description;
        //this.startTime = startTime;
        //this.endTime = endTime;

    }
    public ScheduleContentFragment(int id , String name, String description,String url){
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public ScheduleContentFragment(String name, String description,String url){
        this.name = name;
        this.description = description;
        this.url = url;
    }
    public ScheduleContentFragment(){

    }

    /*
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
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
