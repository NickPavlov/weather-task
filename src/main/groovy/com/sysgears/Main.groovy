package com.sysgears
import com.sysgears.model.coordinates.Coordinates
import com.sysgears.model.coordinates.ICoordinates
import com.sysgears.model.json.IParser
import com.sysgears.model.json.JsonParser
import com.sysgears.model.weather.DarkSkyForecast
import com.sysgears.model.weather.IWeatherUpdater

/**
 * Main class.
 */
class Main {

    /**
     * Developer's personal key
     */
    static final String API_KEY = "16142d05a3314de31b6fce9a83469c06"

    /**
     * Location coordinates.
     */
    static final ICoordinates COORDINATES = new Coordinates(37.8267, 0) //-122.423

    static void main(String[] args) {
        IWeatherUpdater weatherUpdater = new DarkSkyForecast(API_KEY, COORDINATES)
        String response = weatherUpdater.update()
        IParser jsonParser = new JsonParser(response)

        println "Response:\n${response}"

        Map data = (Map) jsonParser.get("currently")
        println "\n\nCurrently\n${jsonParser.get("currently")}\n"
        println "Summary: ${data.get("summary")}"
        println "Temperature: ${data.get("temperature")}"
        println "WindSpeed: ${data.get("windSpeed")}"
        println "humidity: ${data.get("humidity")}"

        data = (Map) jsonParser.get("minutely")
        println "\n\nMinutely:\n"
        int count = 0;
        data.get("data").each {
            println "${count++}) ${it}"
        }

        //println "\n\nMinutely\n${jsonParser.get("minutely")}"
        //println "\n\nHourly\n${jsonParser.get("hourly")}"
        //println "\n\nDaily\n${jsonParser.get("daily")}"
    }
}