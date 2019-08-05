package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 3/28/2016.
 */
public class Notice {
    private int notice_id;
    private String notice_title;
    private String notice_contains;
    private String notice_url;
    private int active_stat;
    private String notice_date_time;
    private int notice_seen_status;
    private String ref_number;
    private String lastUpadte;
    private String post_date;

    public String getNotice_url() {
        return notice_url;
    }

    public void setNotice_url(String notice_url) {
        this.notice_url = notice_url;
    }

    public void setLastUpadte(String lastUpadte) {
        this.lastUpadte = lastUpadte;
    }

    public String getLastUpadte() {
        return lastUpadte;
    }

    public void setNotice_seen_status(int notice_seen_status) {
        this.notice_seen_status = notice_seen_status;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }



    public int getNotice_seen_status() {
        return notice_seen_status;
    }

    public String getRef_number() {
        return ref_number;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setSeen_stat(int seen_stat) {
        this.notice_seen_status = seen_stat;
    }

    public int getSeen_stat() {

        return notice_seen_status;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public String getNotice_contains() {
        return notice_contains;
    }

    public int getActive_stat() {
        return active_stat;
    }



    public String getNotice_date_time() {
        return notice_date_time;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public void setNotice_contains(String notice_contains) {
        this.notice_contains = notice_contains;
    }

    public void setActive_stat(int active_stat) {
        this.active_stat = active_stat;
    }


    public void setNotice_date_time(String notice_date_time) {
        this.notice_date_time = notice_date_time;
    }
}
