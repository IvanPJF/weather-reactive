package ru.job4j.weather.model;

public class Weather {

    private Integer id;
    private String city;
    private Integer temperature;

    public Weather(Integer id, String city, Integer temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public Integer getTemperature() {
        return temperature;
    }
}
