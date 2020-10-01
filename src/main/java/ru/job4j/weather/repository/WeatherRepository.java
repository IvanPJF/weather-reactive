package ru.job4j.weather.repository;

import ru.job4j.weather.model.Weather;

import java.util.Collection;

public interface WeatherRepository {

    Weather findById(Integer id);

    Collection<Weather> findAll();
}
