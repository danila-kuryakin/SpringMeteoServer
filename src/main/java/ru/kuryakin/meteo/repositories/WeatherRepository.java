package ru.kuryakin.meteo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuryakin.meteo.models.weather.Location;
import ru.kuryakin.meteo.models.weather.Weather;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findTop10ByOrderByIdDesc();
    Weather findTopByLocationOrderByIdDesc(Location location);
    List<Weather> findTop300ByLocationOrderByIdDesc(Location location);

}
