package ru.job4j.weather.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.weather.model.Weather;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemWeatherRepository implements WeatherRepository {

    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

    {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "SPb", 15));
        weathers.put(3, new Weather(3, "Bryansk", 15));
        weathers.put(4, new Weather(4, "Smolensk", 15));
        weathers.put(5, new Weather(5, "Kiev", 15));
        weathers.put(6, new Weather(6, "Minsk", 15));
        weathers.put(7, new Weather(7, "Kostroma", 15));
    }

    @Override
    public Weather findById(Integer id) {
        return weathers.get(id);
    }

    @Override
    public Collection<Weather> findAll() {
        return weathers.values();
    }
}
