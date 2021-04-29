package com.example.devmobile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseWeatherFiveD {
    @SerializedName("list")
    private List<Weather> listWeather;

    public ResponseWeatherFiveD(List<Weather> listWeather) {
        this.listWeather = listWeather;
    }

    public List<Weather> getListWeather() {
        return listWeather;
    }

    public void setListWeather(List<Weather> listWeather) {
        this.listWeather = listWeather;
    }
}
