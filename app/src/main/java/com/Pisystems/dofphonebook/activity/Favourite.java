package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 3/27/2016.
 */
public class Favourite {
    private int favourite_id;
    private int user_id;

    public void setFavourite_id(int favourite_id) {
        this.favourite_id = favourite_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFavourite_id() {

        return favourite_id;
    }

    public int getUser_id() {
        return user_id;
    }
}
