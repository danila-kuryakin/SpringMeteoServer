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

/***
 * Реализация взаимодействия с таблицей weather.
 */
@Component
public class WeatherDAO {

    /**
     * Репозиторий.
     */
    @Autowired
    private WeatherRepository weatherRep;

    /**
     * Вернуть все значения.
     * @return Список значений.
     */
    public List<Weather> findAll() {
        return weatherRep.findAll();
    }

    /***
     * Возвращает 10 последних строк из таблицы.
     * @return список со значениями с датчиков.
     */
    public List<Weather> findTop10ByOrderByIdDesc(){
        return weatherRep.findTop10ByOrderByIdDesc();
    }

    /***
     * Возвращает последние строку по местоположению из таблицы.
     * @param location - местоположение.
     * @return значение с датчиков.
     */
    public Weather findByLastWeather(Location location){
        return weatherRep.findTopByLocationOrderByIdDesc(location);
    }

    /***
     * Возвращает 300 последних строк по местоположению из таблицы.
     * @param location - местоположение.
     * @return список со значениями с датчиков.
     */
    public List<Weather> getTop300Current(Location location){
        return weatherRep.findTop300ByLocationOrderByIdDesc(location);
    }
}
