package com.example.devmobile;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("dt")
    private int dt;
    @SerializedName("main")
    private Main main;
    @SerializedName("dt_txt")
    private String dt_txt;

    public Weather(int dt, Main main, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.dt_txt = dt_txt;
    }

    public int getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
