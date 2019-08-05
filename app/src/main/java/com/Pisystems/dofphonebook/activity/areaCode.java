package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 3/19/2016.
 */
public class areaCode {
   private int areaID;
   private int ParentID;
   private String areaName;
   private int active_stat;
   private String Last_update;
   private String areaNameEng;

    public String getAreaNameEng() {
        return areaNameEng;
    }

    public void setAreaNameEng(String areaNameEng) {
        this.areaNameEng = areaNameEng;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setActive_stat(int active_stat) {
        this.active_stat = active_stat;
    }

    public void setLast_update(String last_update) {
        Last_update = last_update;
    }

    public int getAreaID() {
        return areaID;
    }

    public int getParentID() {
        return ParentID;
    }

    public String getAreaName() {
        return areaName;
    }

    public int getActive_stat() {
        return active_stat;
    }

    public String getLast_update() {
        return Last_update;
    }
}
