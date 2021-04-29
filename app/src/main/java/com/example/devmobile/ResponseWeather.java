package com.example.devmobile;

public class ResponseWeather {
    private String name;
    private Main main;

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

}
