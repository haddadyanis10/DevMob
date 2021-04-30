package com.example.devmobile;

import java.util.List;

public class ResponseWeather {
    private String name;
    private Main main;
    private List<WeatherCurrent> weather;

    public ResponseWeather(String name,Main main,List<WeatherCurrent> weather) {
        this.name = name;
        this.main = main;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }

    public List<WeatherCurrent> getWeather() {
        return weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWeather(List<WeatherCurrent> weather) {
        this.weather = weather;
    }
}
