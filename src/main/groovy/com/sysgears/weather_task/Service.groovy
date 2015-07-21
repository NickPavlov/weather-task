package com.sysgears.weather_task

import com.sysgears.weather_task.model.coordinates.Coordinates
import com.sysgears.weather_task.model.http.Http
import com.sysgears.weather_task.model.json.IParser
import com.sysgears.weather_task.model.json.JsonParser
import com.sysgears.weather_task.model.weather.IWeatherForecast

/**
 * The <code>Service</code> class combines logic of the main components.
 */
class Service {

    /**
     * Weather updater.
     */
    final IWeatherForecast weatherUpdater;

    /**
     * Refresh rate in milliseconds.
     */
    int refreshRate;

    /**
     * Creates the <code>Service</code> object with a specified weather updater.
     *
     * @param weatherUpdater
     */
    Service(final IWeatherForecast weatherUpdater) {
        this.weatherUpdater = weatherUpdater
    }

    /**
     * Starts service.
     */
    void start() {
        String response = weatherUpdater.getForecast(new Coordinates(37.8267, -122.423))
        IParser jsonParser = new JsonParser(response)

        println "Response:\n${response}"

        Map data = (Map) jsonParser.get("currently")

        println "\n\nCurrently\n${jsonParser.get("currently")}\n"
        println "WindSpeed: ${data.get("windSpeed")}"


        String url = "https://push.geckoboard.com/v1/send/"
        String windSpeedWidget = "152712-7e44ff8d-e45d-4ca5-a4dc-777166c855ab"
        //String windSpeedWidget = "152712-5d5248f3-f78a-4f2d-b018-546aee106155"

        //Highcharts
        //String windSpeedWidget = "152712-9b44bbf8-d5b7-444e-8e25-ab1b54cb6f4d"

        String windSpeedBody = "{\"api_key\":\"c143d855c29d5fe59d2ce0830c834e04\"," +
                "\"data\":{\"item\":${data.get("windSpeed")},\"min\":{\"value\":0},\"max\":{\"value\":100}}}"

        Map<String, String> headers = new HashMap<String, String>()
        headers.put("Content-Type", "application/json")

        println Http.post(url + windSpeedWidget, headers, windSpeedBody)

    }
}