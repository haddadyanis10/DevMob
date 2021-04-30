package com.example.devmobile;

public class ResponseWeather {
    private String name;
    private Main main;
    /*private Coord coord;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private WeatherSimple weather;*/

    public ResponseWeather(String name,Main main) {
        this.name = name;
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    /*public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public WeatherSimple getWeather() {
        return weather;
    }

    public void setWeather(WeatherSimple weather) {
        this.weather = weather;
    }*/
}
