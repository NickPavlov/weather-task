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
import com.sysgears.weather_task.model.weather.update.UpdateRate

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
    static final String TOMORROW = "tomorrow.js"

    /**
     *
     */
    static final String TEMPERATURE_HOURLY = "temperature_hourly.js"

    /**
     *
     */
    static final String TEMPERATURE_DAILY = "temperature_daily.js"

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
    static final Coordinates COORDINATES = new Coordinates(37.8267, -122.423)

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
            if (minutelyBlock) {
                List minutelyData = (List) minutelyBlock.get("data")
                List<Parameter> parameters = new ArrayList<Parameter>()
                minutelyData.each {
                    map = (Map) it
                    parameters.add(new Parameter(Double.valueOf(map.get("precipProbability").toString()),
                            Long.valueOf(map.get("time").toString())))
                }

                parameters.each { println it }
                println "Update rate: ${UpdateRate.evaluate(parameters, 0.000001)}"
            }

            //-------------------------------------------------------------------------------------------------------Now

            Map currentlyBlock = (Map) jsonParser.get("currently")

            String lineColor = "transparent"

            //Current temperature
            String currentTemperature = currentlyBlock.get("temperature").toString()
            String celsius = Converter.toCelsius(Double.valueOf(currentTemperature)) + " ${Text.CELSIUS_SIGN}C"
            Plot currentTemperaturePlot = new Plot(celsius, lineColor, true, [])
            //Current summary
            Plot currentSummaryPlot = new Plot(currentlyBlock.get("summary").toString(), lineColor, true, [])

            Highchart nowHighchart = Highchart.createFromFile(getResource(NOW),
                    Widgets.NOW, API_KEY)


            println "\nNow:"
            println Http.post(nowHighchart.getRequestURL(),
                    HEADERS, nowHighchart.getConfig([currentTemperaturePlot, currentSummaryPlot]))


            //----------------------------------------------------------------------------------------------------Hourly
            Map hourlyBlock = (Map) jsonParser.get("hourly")
            List data = (List) hourlyBlock.get("data")
            List temperature_day = new ArrayList<Double>()

            data.each({
                map = (Map) it
                temperature_day.add([Text.formatTime(map.get("time").toString(), TIME_FORMAT),
                                     Converter.toCelsius(Double.valueOf(map.get("temperature").toString()))])
            })
            Plot hourlyTemperaturePlot = new Plot("${Text.CELSIUS_SIGN}t", "#FFAF22", true, temperature_day)
            Highchart hourlyTemperatureHighchart = Highchart.createFromFile(getResource(TEMPERATURE_HOURLY),
                    Widgets.TEMPERATURE_HOURLY, API_KEY)

            println "\nHourly:"
            println Http.post(hourlyTemperatureHighchart.getRequestURL(), HEADERS,
                    hourlyTemperatureHighchart.getConfig([hourlyTemperaturePlot]))

            //-----------------------------------------------------------------------------------------------------Daily

            Map dailyBlock = (Map) jsonParser.get("daily")
            List dailyData = (List) dailyBlock.get("data")
            List<List> minTemperatureDaily = new ArrayList<List>()
            List<List> maxTemperatureDaily = new ArrayList<List>()

            dailyData.each({
                map = (Map) it
                minTemperatureDaily.add([Text.formatTime(map.get("time").toString(), TIME_FORMAT),
                                         Converter.toCelsius(Double.valueOf(map.get("temperatureMin").toString()))])

                maxTemperatureDaily.add([Text.formatTime(map.get("time").toString(), TIME_FORMAT),
                                         Converter.toCelsius(Double.valueOf(map.get("temperatureMax").toString()))])
            })

            Plot minTemperaturePlot = new Plot("Min ${Text.CELSIUS_SIGN}t", "#5874FF", true, minTemperatureDaily)
            Plot maxTemperaturePlot = new Plot("Max ${Text.CELSIUS_SIGN}t", "#FF5858", true, maxTemperatureDaily)
            Highchart dailyTemperatureHighchart = Highchart.createFromFile(getResource(TEMPERATURE_DAILY),
                    Widgets.TEMPERATURE_DAILY, API_KEY)

            println "\nDaily:"
            println Http.post(dailyTemperatureHighchart.getRequestURL(), HEADERS,
                    dailyTemperatureHighchart.getConfig([minTemperaturePlot, maxTemperaturePlot]))


            //--------------------------------------------------------------------------------------------------Tomorrow

            //Tomorrow min/max temperature
            StringBuilder tomorrowTemperature = new StringBuilder()
            tomorrowTemperature.append(minTemperatureDaily.get(1).get(1))
                    .append(Text.CELSIUS_SIGN)
                    .append("C")
                    .append("...")
                    .append(maxTemperatureDaily.get(1).get(1))
                    .append(" ")
                    .append(Text.CELSIUS_SIGN)
                    .append("C")

            Plot tomorrowMinMaxTPlot = new Plot(tomorrowTemperature.toString(), lineColor, true, [])
            //Tomorrow summary
            map = (Map) dailyData.get(0)
            println map.get("summary").toString()
            Plot tomorrowSummaryPlot = new Plot(map.get("summary").toString(), lineColor, true, [])

            Highchart tomorrowHighchart = Highchart.createFromFile(getResource(TOMORROW),
                    Widgets.TOMORROW, API_KEY)


            println "\nNow:"
            println Http.post(tomorrowHighchart.getRequestURL(),
                    HEADERS, tomorrowHighchart.getConfig([tomorrowMinMaxTPlot, tomorrowSummaryPlot]))


        //}

    }

    /**
     * Stops the service.
     */
    synchronized void stop() {

    }

    private static String getResource(final String resource) {
        RESOURCES + resource
    }
}