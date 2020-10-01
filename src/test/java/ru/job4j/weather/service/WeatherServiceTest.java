package ru.job4j.weather.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;
import ru.job4j.weather.Main;
import ru.job4j.weather.model.Weather;
import ru.job4j.weather.repository.WeatherRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = Main.class)
public class WeatherServiceTest {

    @MockBean
    private WeatherRepository repository;

    @Test
    public void whenFindAllThen2Weathers() {
        WeatherService service = new WeatherService(repository);
        List<Weather> weathers = List.of(
                new Weather(1, "Msc", 20),
                new Weather(2, "Spb", 15)
        );
        when(repository.findAll()).thenReturn(weathers);
        StepVerifier.create(service.findAll())
                .expectNext(weathers.get(0))
                .expectNext(weathers.get(1))
                .expectComplete()
                .verify();
    }

    @Test
    public void whenFindById1Then1Weathers() {
        WeatherService service = new WeatherService(repository);
        int id = 1;
        Weather weather = new Weather(1, "Msc", 20);
        when(repository.findById(id)).thenReturn(weather);
        StepVerifier.create(service.findById(id))
                .expectNext(weather)
                .expectComplete()
                .verify();
    }

    @Test
    public void whenFindByMaxTemperatureThen1Weathers() {
        WeatherService service = new WeatherService(repository);
        List<Weather> weathers = List.of(
                new Weather(1, "Msc", 20),
                new Weather(2, "Spb", 15)
        );
        when(repository.findAll()).thenReturn(weathers);
        StepVerifier.create(service.findByMaxTemperature())
                .expectNext(weathers.get(0))
                .expectComplete()
                .verify();
    }

    @Test
    public void whenFindAllByGreatTemperature12Then2Weathers() {
        WeatherService service = new WeatherService(repository);
        List<Weather> weathers = List.of(
                new Weather(1, "Msc", 20),
                new Weather(1, "Murmansk", 10),
                new Weather(2, "Spb", 15)
        );
        when(repository.findAll()).thenReturn(weathers);
        StepVerifier.create(service.findAllByGreatTemperature(12))
                .expectNext(weathers.get(0))
                .expectNext(weathers.get(2))
                .expectComplete()
                .verify();
    }
}