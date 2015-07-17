package com.sysgears

import com.sysgears.model.coordinates.Coordinates
import com.sysgears.model.http.Http
import com.sysgears.model.json.IParser
import com.sysgears.model.json.JsonParser
import com.sysgears.model.weather.IWeatherUpdater

/**
 * The <code>Service</code> class provides...
 */
class Service {

    /**
     * Weather updater.
     */
    final IWeatherUpdater weatherUpdater;

    /**
     * Refresh rate in milliseconds.
     */
    int refreshRate;

    /**
     * Creates the <code>Service</code> object with a specified weather updater.
     *
     * @param weatherUpdater
     */
    Service(final IWeatherUpdater weatherUpdater) {
        this.weatherUpdater = weatherUpdater
    }

    /**
     * Starts service.
     */
    void start() {

        weatherUpdater.setLocation(new Coordinates(37.8267, -122.423))

        String response = weatherUpdater.getForecast()
        IParser jsonParser = new JsonParser(response)

        println "Response:\n${response}"

        Map data = (Map) jsonParser.get("currently")

        println "\n\nCurrently\n${jsonParser.get("currently")}\n"
        println "WindSpeed: ${data.get("windSpeed")}"


        String url = "https://push.geckoboard.com/v1/send/"
        String windSpeedWidget = "152712-5d5248f3-f78a-4f2d-b018-546aee106155"


        //Highcharts
        //String windSpeedWidget = "152712-9b44bbf8-d5b7-444e-8e25-ab1b54cb6f4d"

        String windSpeedBody = "{\"api_key\":\"c143d855c29d5fe59d2ce0830c834e04\"," +
                "\"data\":{\"item\":${data.get("windSpeed")},\"min\":{\"value\":0},\"max\":{\"value\":100}}}"

        Map<String, String> headers = new HashMap<String, String>()
        headers.put("Content-Type", "application/json")

        Http.post(url + windSpeedWidget, headers, windSpeedBody)

        /*
        data = (Map) jsonParser.get("minutely")
        println "\n\nMinutely:\n"
        int count = 0;
        Map temp
        data.get("data").each {
            temp = (Map) it
            println "${count++}) PrecipProbability: ${temp.get("precipProbability")} PrecipIntensity: ${temp.get("precipIntensity")}"
        }

        data = (Map) jsonParser.get("hourly")
        println "\n\nHourly:\n"
        count = 0;
        data.get("data").each {
            temp = (Map) it
            println "${count++}) Temperature: ${temp.get("temperature")} WindSpeed: ${temp.get("windSpeed")}"
        }

        println "\n\nMinutely\n${jsonParser.get("minutely")}"
        println "\n\nHourly\n${jsonParser.get("hourly")}"
        println "\n\nDaily\n${jsonParser.get("daily")}"
        */
    }
}