package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 4/19/2016.
 */
public class area_info {
    private int area_id;
    private String area_details;
    private String area_location;
    private int active_stat;
    private String Last_update;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public void setArea_details(String area_details) {
        this.area_details = area_details;
    }

    public void setArea_location(String area_location) {
        this.area_location = area_location;
    }

    public void setActive_stat(int active_stat) {
        this.active_stat = active_stat;
    }

    public void setLast_update(String last_update) {
        Last_update = last_update;
    }

    public int getArea_id() {
        return area_id;
    }

    public String getArea_details() {
        return area_details;
    }

    public String getArea_location() {
        return area_location;
    }

    public int getActive_stat() {
        return active_stat;
    }

    public String getLast_update() {
        return Last_update;
    }
}
