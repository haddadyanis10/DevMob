package com.example.devmobile;

import com.google.gson.annotations.SerializedName;

public class
WeatherSimple {
    @SerializedName("id")
    private float id;
    @SerializedName("main")
    private float main;
    @SerializedName("description")
    private float description;
    @SerializedName("icon")
    private float icon;

    public WeatherSimple(float id, float main, float description, float icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public float getMain() {
        return main;
    }

    public void setMain(float main) {
        this.main = main;
    }

    public float getDescription() {
        return description;
    }

    public void setDescription(float description) {
        this.description = description;
    }

    public float getIcon() {
        return icon;
    }

    public void setIcon(float icon) {
        this.icon = icon;
    }
}
