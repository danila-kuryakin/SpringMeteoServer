//package ru.kuryakin.meteo.collector;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import ru.kuryakin.meteo.MeteoApplication;
//import ru.kuryakin.meteo.dao.WeatherDAO;
//import ru.kuryakin.meteo.models.weather.Location;
//import ru.kuryakin.meteo.models.weather.Weather;
//
//import java.io.IOException;
//import java.net.ConnectException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.TimerTask;
//
//
//@Controller
//public class Collector extends TimerTask {
//
//    private static final Logger logger = LoggerFactory.getLogger(MeteoApplication.class);
//
//    private WeatherDAO weatherDAO;
//
//    @Autowired
//    public Collector(WeatherDAO weatherDAO) {
//        this.weatherDAO = weatherDAO;
//    }
//
//    @Override
//    public void run() {
//        logger.info("Start collector");
//        List<String> urls = new ArrayList();
//        urls.add("http://192.168.0.104/1");
//        urls.add("http://192.168.0.104/2");
//        urls.add("http://192.168.0.103/");
//        for (String url:urls) {
//            if (!getWeatherFromController(url))
//                logger.info(String.format("Collector don't get weather data at the ip(%s)", url));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public boolean getWeatherFromController(String url){
//        JSONParser parser = new JSONParser();
//        boolean sensorStatus = false;
//        Location location;
//        try {
//            Document doc = Jsoup.connect(url)
//                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                    .referrer("http://www.google.com")
//                    .get();
//            Elements listNews = doc.select("body");
//            Weather weather = new Weather();
//            JSONObject jsonWeather = (JSONObject) parser.parse(listNews.text());
//            Object tempObject = jsonWeather.get("Temperature");
//            if (tempObject instanceof Double)
//                weather.setTemperature((Double) tempObject);
//            else {
//                logger.info("Fail: Temperature not recognized");
//            }
//
//            Object humidObject = jsonWeather.get("Humidity");
//            if (humidObject instanceof Double) {
//                weather.setHumidity((Double) humidObject);
//                sensorStatus = true;
//            }
//            Object pressureObject = jsonWeather.get("Pressure");
//            if (pressureObject instanceof Double) {
//                weather.setPressure((Double) pressureObject);
//                sensorStatus = true;
//            }
//            if (!sensorStatus){
//                logger.info("Fail: Pressure or Humidity not recognized");
//            }
//
//            Object locationObject = jsonWeather.get("Location");
//            location = Location.valueOf(String.valueOf(locationObject));
//            boolean locationExists = false;
//            for (Location value:Location.values()) {
//                if (location.equals(value)) {
//                    weather.setLocation(location);
//                    locationExists = true;
//                }
//            }
//            if (!locationExists){
//                logger.info("Fail: Location does not exists");
//            }
//            weatherDAO.add(weather);
//            return true;
//        }catch (ConnectException e){
//            System.out.println(e.getMessage());
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
