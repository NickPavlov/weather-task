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

        String response = weatherUpdater.update()
        IParser jsonParser = new JsonParser(response)

        println "Response:\n${response}"

        Map data = (Map) jsonParser.get("currently")

        println "\n\nCurrently\n${jsonParser.get("currently")}\n"
        println "Summary: ${data.get("summary")}"
        println "Temperature: ${data.get("temperature")}"
        println "WindSpeed: ${data.get("windSpeed")}"
        println "humidity: ${data.get("humidity")}"

        String url = "https://push.geckoboard.com/v1/send/"

        String windSpeedWidget = "152712-5d5248f3-f78a-4f2d-b018-546aee106155"
        String temperatureWidget = "152712-ecb527c2-540e-4894-97db-23f5cc6f1d9b"


        String temperatureBody = "{\"api_key\":\"c143d855c29d5fe59d2ce0830c834e04\"," +
                "\"data\":{\"item\":${data.get("temperature")},\"min\":{\"value\":0},\"max\":{\"value\":100}}}"

        String windSpeedBody = "{\"api_key\":\"c143d855c29d5fe59d2ce0830c834e04\"," +
                "\"data\":{\"item\":${data.get("windSpeed")},\"min\":{\"value\":0},\"max\":{\"value\":100}}}"



        Map<String, String> headers = new HashMap<String, String>()
        headers.put("Content-Type", "application/json")

        Http.post(url + temperatureWidget, headers, temperatureBody)
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