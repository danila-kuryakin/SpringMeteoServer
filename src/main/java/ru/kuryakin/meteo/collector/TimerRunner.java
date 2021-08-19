//package ru.kuryakin.meteo.collector;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.kuryakin.meteo.MeteoApplication;
//import ru.kuryakin.meteo.dao.WeatherDAO;
//
//import java.util.GregorianCalendar;
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//@Component
//public class TimerRunner implements CommandLineRunner {
//    private static final Logger logger = LoggerFactory.getLogger(MeteoApplication.class);
//    public static final int MINUTES_DELAY = 5;
//
//    @Autowired
//    private WeatherDAO weatherDAO;
//
//    @Override
//    public void run(String... args){
//        TimerTask timerTask = new Collector(weatherDAO);
//        Timer timer = new Timer("CollectorWeatherTimer", true);
//        if (MINUTES_DELAY > 0)
//            timer.scheduleAtFixedRate(timerTask, synchronizer(MINUTES_DELAY), MINUTES_DELAY*60*1000);
//    }
//
//    private long synchronizer(long period){
//        long minute, second, millisecond, deltaMin, deltaSec, delayMil, delta;
//        GregorianCalendar calendar = new GregorianCalendar();
//
//        minute = calendar.get(GregorianCalendar.MINUTE);
//        second = calendar.get(GregorianCalendar.SECOND);
//        millisecond = calendar.get(GregorianCalendar.MILLISECOND);
//
//        deltaMin = minute / period * period + period - minute - 1;
//        deltaSec = 59 - second;
//        delayMil = 1000 - millisecond;
//
//        delta = (deltaMin * 60*1000) + (deltaSec * 1000) + delayMil;
//
//        logger.info(String.format("Launch collector in %d:%d.%d or %d", deltaMin, deltaSec, delayMil, delta));
//
//        return delta;
//    }
//}
