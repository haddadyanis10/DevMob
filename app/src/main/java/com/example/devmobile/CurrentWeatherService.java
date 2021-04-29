package com.example.devmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrentWeatherService {
    @GET("data/2.5/weather")
    Call<ResponseWeather> getByCity(@Query("q") String city,@Query("appid") String apikey,@Query("units") String units);

    @GET("data/2.5/forecast")
    Call<ResponseWeatherFiveD> getForFiveD(@Query("q") String city,@Query("appid") String apikey,@Query("units") String units);
}
