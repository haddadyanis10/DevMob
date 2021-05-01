package com.example.devmobile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    @SerializedName("dt")
    private int dt;
    @SerializedName("main")
    private Main main;
    @SerializedName("dt_txt")
    private String dt_txt;
    @SerializedName("weather")
    private List<WeatherCurrent> weather;

    public Weather(int dt, Main main, String dt_txt, List<WeatherCurrent> weather) {
        this.dt = dt;
        this.main = main;
        this.dt_txt = dt_txt;
        this.weather = weather;
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

    public List<WeatherCurrent> getWeather() {
        return weather;
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

    public void setWeather(List<WeatherCurrent> weather) {
        this.weather = weather;
    }
}
