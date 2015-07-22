package com.sysgears.weather_task
import com.sysgears.weather_task.model.coordinates.Coordinates
import com.sysgears.weather_task.model.highcharts.Highchart
import com.sysgears.weather_task.model.highcharts.Widgets
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
     * Resources.
     */
    static final String RESOURCES = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/"

    /**
     *
     */
    static final String TEMPERATURE_CURRENT = "temperature_current.js"

    /**
     *
     */
    static final String SUMMARY_CURRENT = "summary_current.js"

    /**
     *
     */
    static final String TEMPERATURE_DAY = "temperature_day.js"

    /**
     * Developer's personal key.
     */
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"

    /**
     * Widget unique key.
     */
    static final WIDGET = "152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

    /**
     * Current coordinates.
     */
    static final Coordinates COORDINATES = new Coordinates(48.455329, 35.035030)

    /**
     * Time format.
     */
    static final String TIME_FORMAT = "HH:mm dd-MM-yyyy"

    /**
     * Http headers.
     */
    static final Map HEADERS = ["Content-Type": "application/json"]

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
     * Starts the service.
     */
    void start() {
        String response = weatherUpdater.getForecast(COORDINATES)
        IParser jsonParser = new JsonParser(response)

        //----------------------------------------------
        Map currently = (Map) jsonParser.get("currently")
        //println currently.get("temperature")
        //println currently.get("summary")

        Plot tCurrent = new Plot((String) currently.get("temperature"), "transparent", true, [])
        Highchart hTemperature_current = Highchart.createFromFile(getResource(TEMPERATURE_CURRENT),
                Widgets.TEMPERATURE_CURRENT, API_KEY)
        println Http.post(hTemperature_current.getRequestURL(), HEADERS, hTemperature_current.getConfig([tCurrent]))

        Plot sCurrent = new Plot((String) currently.get("summary"), "transparent", true, [])
        Highchart hSummary_current = Highchart.createFromFile(getResource(SUMMARY_CURRENT),
                Widgets.SUMMARY_CURRENT, API_KEY)

        println Http.post(hSummary_current.getRequestURL(), HEADERS, hSummary_current.getConfig([sCurrent]))
        //----------------------------------------------
        Map hourly = (Map) jsonParser.get("hourly")
        List data = (List) hourly.get("data")
        List temperature_day = new ArrayList<Double>()

        Map map
        data.each({
            map = (Map) it
            temperature_day.add([Text.formatTime(map.get("time").toString(), TIME_FORMAT),
                             Converter.toCelsius(Double.valueOf(map.get("temperature").toString()))])
        })
        Plot t = new Plot("temperature", "#C72B2B", true, temperature_day)
        Highchart hTemperature_day = Highchart.createFromFile(getResource(TEMPERATURE_DAY),
                Widgets.TEMPERATURE_FORECAST, API_KEY)
        println Http.post(hTemperature_day.getRequestURL(), HEADERS, hTemperature_day.getConfig([t]))
        //----------------------------------------------

    }

    private static String getResource(final String resource) {
        RESOURCES + resource
    }
}