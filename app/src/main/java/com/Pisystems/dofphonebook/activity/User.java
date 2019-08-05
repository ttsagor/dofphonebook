package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 3/14/2016.
 */
public class User {
    private int user_id;
    private String user_name;
    private String designation;
    private int designationID;
    private int area_id;
    private String phone1;
    private String phone2;
    private String office_num;
    private String email;
    private String org_id;
    private int active_stat;
    private String last_update;
    private String user_name_eng;

    public int getDesignationID() {
        return designationID;
    }

    public void setDesignationID(int designationID) {
        this.designationID = designationID;
    }

    public void setUser_name_eng(String user_name_eng) {
        this.user_name_eng = user_name_eng;
    }

    public String getUser_name_eng() {

        return user_name_eng;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setOffice_num(String office_num) {
        this.office_num = office_num;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public void setActive_stat(int active_stat) {
        this.active_stat = active_stat;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getDesignation() {
        return designation;
    }

    public int getArea_id() {
        return area_id;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getOffice_num() {
        return office_num;
    }

    public String getEmail() {
        return email;
    }

    public String getOrg_id() {
        return org_id;
    }

    public int getActive_stat() {
        return active_stat;
    }

    public String getLast_update() {
        return last_update;
    }
}
