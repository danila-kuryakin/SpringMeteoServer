package ru.kuryakin.meteo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kuryakin.meteo.MeteoApplication;
import ru.kuryakin.meteo.dao.WeatherDAO;
import ru.kuryakin.meteo.models.weather.Location;
import ru.kuryakin.meteo.models.weather.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WeatherController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
    private static final Logger logger = LoggerFactory.getLogger(MeteoApplication.class);

    boolean showTable;

    @Autowired
    private WeatherDAO weatherDAO;

    public WeatherController() {
        logger.info("Staaaaart!!!!");
        showTable = true;
    }

    public WeatherController(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
        logger.info("Staaaaart!!!!");
        showTable = true;
    }

    @GetMapping("/weather")
    public String getAllRecords(Model  model){
        List<Weather> lastLocalCurrents = new ArrayList();
        for (Location location:Location.values()) {
            lastLocalCurrents.add(weatherDAO.findByLastWeather(location));
        }
        model.addAttribute("locations", lastLocalCurrents);
        model.addAttribute("weathers", weatherDAO.findTop10ByOrderByIdDesc());
        return "weather/index";
    }

    @GetMapping("/weather/{name}")
    public String show(@PathVariable("name") String name, Model model) {
        List<Weather> lastLocalCurrents = new ArrayList();
        for (Location location:Location.values()) {
            lastLocalCurrents.add(weatherDAO.findByLastWeather(location));
        }
        model.addAttribute("locations", lastLocalCurrents);

        List<Weather> top = weatherDAO.getTop300Current(Location.valueOf(name));
        List<String> date = new ArrayList<>();
        List<Double> temperature = new ArrayList<>();
        List<Double> humidity = new ArrayList<>();
        List<Double> pressure = new ArrayList<>();
        boolean notNullHum = false, notNullPres = false;
        for (int i = top.size()-1; i >= 0; i--) {
            date.add(String.format(dateFormat.format(top.get(i).getDate().getTime())));
            temperature.add(top.get(i).getTemperature());
            humidity.add(top.get(i).getHumidity());
            if (top.get(i).getHumidity() != null) notNullHum = true;
            pressure.add(top.get(i).getPressure());
            if (top.get(i).getPressure() != null) notNullPres = true;
        }
        model.addAttribute("date", date);
        model.addAttribute("temperature", temperature);
        if (notNullHum) model.addAttribute("humidity", humidity);
        if (notNullPres) model.addAttribute("pressure", pressure);
        model.addAttribute("last", top.get(0));
        return "weather/status";
    }
}
