package com.example.luoshuimumu.TopNews.gank.entity;

import com.example.luoshuimumu.TopNews.gank.entity.response.Icon;

import java.util.Date;
import java.util.List;

/**
 * Created by luoshuimumu on 2018/1/11.
 */

public class Weather {
    String cityName;
    Date date;
    String temperature;
    String humidity;
    String climate;
    String wind_direction;
    String hurricane;
    List<Icon> icons;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getHurricane() {
        return hurricane;
    }

    public void setHurricane(String hurricane) {
        this.hurricane = hurricane;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }
}
