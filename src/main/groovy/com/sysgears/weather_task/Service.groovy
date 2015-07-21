package com.sysgears.weather_task

import com.sysgears.weather_task.model.coordinates.Coordinates
import com.sysgears.weather_task.model.highcharts.Highchart
import com.sysgears.weather_task.model.highcharts.plot.Plot
import com.sysgears.weather_task.model.http.Http
import com.sysgears.weather_task.model.parser.IParser
import com.sysgears.weather_task.model.parser.JsonParser
import com.sysgears.weather_task.model.utils.Converter
import com.sysgears.weather_task.model.utils.Text
import com.sysgears.weather_task.model.weather.IWeatherForecast

/**
 * The <code>Service</code> class combines logic of the main components.
 */
class Service {

    /**
     *
     */
    static final PATH = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/temperature.js"    // -----------Bad!!!

    /**
     * Developer's personal key.
     */
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"

    /**
     * Widget unique key.
     */
    static final WIDGET = "152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

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

        Map hourly = (Map) jsonParser.get("hourly")
        List data = (List) hourly.get("data")

        List temperature = new ArrayList<Double>()
        Map map;
        data.each({
            map = (Map) it
            temperature.add([Text.formatTime(map.get("time").toString(), "HH:mm dd-MM-yyyy"),
                             Converter.toCelsius(Double.valueOf(map.get("temperature").toString()))])
        })

        Plot t = new Plot("temperature", "#C72B2B", temperature)
        Highchart hchart = Highchart.createFromFile(PATH, WIDGET, API_KEY)
        Map headers = ["Content-Type": "application/json"]
        String config = hchart.getConfig([t])
        //println config
        println Http.post(hchart.getRequestURL(), headers, config)
    }
}