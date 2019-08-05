package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 4/23/2016.
 */
public class SystemInfo {
    private int dataLoaded;
    private int userID;
    private String userLastUpdate;
    private String areaCodeLastUpdate;
    private String areaInfoLastUpdate;
    private String noticeLastUpdate;
    private String lastUpdate;
    private String designationLastUpdate;

    private int userinfoUpdating;
    private int areacodeUpdating;
    private int areainfoUpdating;
    private int designUpdating;


    public void setUserinfoUpdating(int userinfoUpdating) {
        this.userinfoUpdating = userinfoUpdating;
    }

    public void setAreacodeUpdating(int areacodeUpdating) {
        this.areacodeUpdating = areacodeUpdating;
    }

    public void setAreainfoUpdating(int areainfoUpdating) {
        this.areainfoUpdating = areainfoUpdating;
    }

    public void setDesignUpdating(int designUpdate) {
        this.designUpdating = designUpdate;
    }

    public int getUserinfoUpdating() {
        return userinfoUpdating;
    }

    public int getAreacodeUpdating() {
        return areacodeUpdating;
    }

    public int getAreainfoUpdating() {
        return areainfoUpdating;
    }

    public int getDesignUpdating() {
        return designUpdating;
    }

    public void setDesignationLastUpdate(String designationLastUpdate) {
        this.designationLastUpdate = designationLastUpdate;
    }

    public String getDesignationLastUpdate() {
        return designationLastUpdate;
    }

    public void setUserLastUpdate(String userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public void setAreaCodeLastUpdate(String areaCodeLastUpdate) {
        this.areaCodeLastUpdate = areaCodeLastUpdate;
    }

    public void setAreaInfoLastUpdate(String areaInfoLastUpdate) {
        this.areaInfoLastUpdate = areaInfoLastUpdate;
    }

    public void setNoticeLastUpdate(String noticeLastUpdate) {
        this.noticeLastUpdate = noticeLastUpdate;
    }

    public String getUserLastUpdate() {

        return userLastUpdate;
    }

    public String getAreaCodeLastUpdate() {
        return areaCodeLastUpdate;
    }

    public String getAreaInfoLastUpdate() {
        return areaInfoLastUpdate;
    }

    public String getNoticeLastUpdate() {
        return noticeLastUpdate;
    }



    public void setDataLoaded(int dataLoaded) {
        this.dataLoaded = dataLoaded;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public int getDataLoaded() {
        return dataLoaded;
    }

    public int getUserID() {
        return userID;
    }


}
