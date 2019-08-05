package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 5/11/2016.
 */
public class Designation {

    private int design_id;
    private String design_en;
    private String design_bn;
    private String design_rank;
    private int active_stats;
    private String last_update;

    public int getDesign_id() {
        return design_id;
    }

    public String getDesign_en() {
        return design_en;
    }

    public String getDesign_bn() {
        return design_bn;
    }

    public String getDesign_rank() {
        return design_rank;
    }

    public int getActive_stats() {
        return active_stats;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setDesign_id(int design_id) {
        this.design_id = design_id;
    }

    public void setDesign_en(String design_en) {
        this.design_en = design_en;
    }

    public void setDesign_bn(String design_bn) {
        this.design_bn = design_bn;
    }

    public void setDesign_rank(String design_rank) {
        this.design_rank = design_rank;
    }

    public void setActive_stats(int active_stats) {
        this.active_stats = active_stats;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
