package ru.job4j.weather.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weather.model.Weather;
import ru.job4j.weather.repository.WeatherRepository;

@Service
public class WeatherService {

    private final WeatherRepository repository;

    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    public Flux<Weather> findAll() {
        return Flux.fromIterable(repository.findAll());
    }

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(repository.findById(id));
    }

    public Mono<Weather> findByMaxTemperature() {
        return Flux.fromIterable(repository.findAll())
                .reduce((w1, w2) -> w1.getTemperature() > w2.getTemperature() ? w1 : w2);
    }

    public Flux<Weather> findAllByGreatTemperature(Integer temperature) {
        return Flux.fromIterable(repository.findAll())
                .filter(w -> w.getTemperature() > temperature);
    }
}
