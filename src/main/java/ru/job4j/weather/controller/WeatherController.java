package ru.job4j.weather.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather.model.Weather;
import ru.job4j.weather.service.WeatherService;

import java.time.Duration;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findAll() {
        Flux<Weather> weathers = service.findAll();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(weathers, delay).map(Tuple2::getT1);
    }

    @GetMapping("/{id}")
    public Mono<Weather> findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping("/hottest")
    public Mono<Weather> findByMaxTemperature() {
        return service.findByMaxTemperature();
    }

    @GetMapping(value = "/cityGreatThen/{temperature}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findAllByGreatTemperature(@PathVariable("temperature") Integer temperature) {
        return service.findAllByGreatTemperature(temperature);
    }
}
