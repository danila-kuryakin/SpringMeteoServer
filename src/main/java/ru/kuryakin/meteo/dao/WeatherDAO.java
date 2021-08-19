package ru.kuryakin.meteo.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.models.weather.Location;
import ru.kuryakin.meteo.models.weather.Weather;
import ru.kuryakin.meteo.repositories.WeatherRepository;

import javax.persistence.EntityManager;
import java.util.GregorianCalendar;
import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
@Component
public class WeatherDAO {

    @Autowired
    private WeatherRepository weatherRep;

    @Autowired
    private EntityManager entityManager;


    public List<Weather> findAll() {
        return weatherRep.findAll();
    }

    public List<Weather> findTop10ByOrderByIdDesc(){
        return weatherRep.findTop10ByOrderByIdDesc();
    }

    public Weather findByLastWeather(Location location){
        return weatherRep.findTopByLocationOrderByIdDesc(location);
    }

    public List<Weather> getTop300Current(Location location){
        return weatherRep.findTop300ByLocationOrderByIdDesc(location);
    }

    public void add(Weather weather) {
        GregorianCalendar date = new GregorianCalendar();
        weather.setDate(date);
        weatherRep.save(weather);
    }
}
