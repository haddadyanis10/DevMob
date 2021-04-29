package com.example.devmobile;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Weather> getListWeatherFiveDays(){
        List<Weather> finalList = new ArrayList<Weather>();
        ArrayList<String> listeJours = Utils.datesFiveDays();
        for (Weather w:this.getListWeather()){
            String date_txt = w.getDt_txt();
            String [] tokens = date_txt.split("\\s+");
            String date = tokens[0];
            String heure = tokens[1];
            if(heure.equals("12:00:00") && listeJours.contains(date))
            {
                finalList.add(w);
            }
        }
        return finalList;
    }

}
