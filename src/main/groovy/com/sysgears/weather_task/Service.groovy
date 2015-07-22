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
import com.sysgears.weather_task.model.weather.update.Parameter

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
    static final String NOW = "now.js"

    /**
     *
     */
    static final String TEMPERATURE_HOURLY = "temperature_hourly.js"

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
    //static final Coordinates COORDINATES = new Coordinates(48.455329, 35.035030)
    static final Coordinates COORDINATES = new Coordinates(37.8267,-122.423)

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
    synchronized void start() {
        //infinite loop!
        //while (true) {
            String response = weatherUpdater.getForecast(COORDINATES)
            println response
            IParser jsonParser = new JsonParser(response)

            Map map

            //Update rate
            Map minutelyBlock = (Map) jsonParser.get("minutely")
            List minutelyData = (List) minutelyBlock.get("data")
            List<Parameter> parameters = new ArrayList<Parameter>()
            minutelyData.each {
                map = (Map) it
                parameters.add(new Parameter(Double.valueOf(map.get("precipProbability").toString()),
                        Long.valueOf(map.get("time").toString())))
            }

            //Current data

            Map currentlyBlock = (Map) jsonParser.get("currently")

            String lineColor = "transparent"

            //Current temperature
            String celsiusTemperature = Converter.toCelsius(Double.valueOf(currentlyBlock.get("temperature").toString())) + " \\\u00B0C"
            Plot currentTemperaturePlot = new Plot(celsiusTemperature, lineColor, true, [])
            //Current summary
            Plot currentSummaryPlot = new Plot(currentlyBlock.get("summary").toString(), lineColor, true, [])

            Highchart todayHighchart = Highchart.createFromFile(getResource(NOW),
                    Widgets.NOW, API_KEY)


            println "\nCurrent summary:"
            println Http.post(todayHighchart.getRequestURL(),
                    HEADERS, todayHighchart.getConfig([currentTemperaturePlot, currentSummaryPlot]))



            //Forecast


            Map hourlyBlock = (Map) jsonParser.get("hourly")
            List data = (List) hourlyBlock.get("data")
            List temperature_day = new ArrayList<Double>()

            data.each({
                map = (Map) it
                temperature_day.add([Text.formatTime(map.get("time").toString(), TIME_FORMAT),
                                     Converter.toCelsius(Double.valueOf(map.get("temperature").toString()))])
            })
            Plot t = new Plot("temperature", "#FFAF22", true, temperature_day)
            Highchart hTemperature_day = Highchart.createFromFile(getResource(TEMPERATURE_HOURLY),
                    Widgets.TEMPERATURE_HOURLY, API_KEY)

            println "\nTemperature forecast:"
            println Http.post(hTemperature_day.getRequestURL(), HEADERS, hTemperature_day.getConfig([t]))
        //}

    }

    synchronized void stop() {

    }

    private static String getResource(final String resource) {
        RESOURCES + resource
    }
}