package com.example.devmobile;

import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    private float all;

    public Clouds(float all) {
        this.all = all;
    }

    public float getAll() {
        return all;
    }

    public void setAll(float all) {
        this.all = all;
    }
}
