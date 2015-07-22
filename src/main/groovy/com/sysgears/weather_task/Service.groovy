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
     * Now widget configuration file.
     */
    static final String NOW = "now.js"

    /**
     * Today widget configuration file.
     */
    static final String TODAY = "today.js"

    /**
     * Tomorrow widget configuration file.
     */
    static final String TOMORROW = "tomorrow.js"

    /**
     * Temperature_hourly widget configuration file.
     */
    static final String TEMPERATURE_HOURLY = "temperature_hourly.js"

    /**
     * Temperature_daily widget configuration file.
     */
    static final String TEMPERATURE_DAILY = "temperature_daily.js"

    /**
     * Developer's personal key.
     */
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"

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
     * Default update rate in milliseconds.
     */
    static final long DEFAULT_UPDATE_RATE = 2000

    /**
     * Weather updater.
     */
    final IWeatherForecast weatherUpdater;

    /**
     * Service running state.
     */
    boolean isRunning

    /**
     * Refresh rate in milliseconds.
     */
    long refreshRate

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
        isRunning = true

        refreshRate = DEFAULT_UPDATE_RATE

        String response
        IParser jsonParser
        Map temp

        Map minutelyBlock
        List minutelyData
        List<Parameter> parameters

        Map currentlyBlock
        String lineColor = "transparent"
        String currentTemperature
        String celsius
        Plot currentTemperaturePlot
        Plot currentSummaryPlot
        Highchart nowHighchart

        Map hourlyBlock
        List hourlyData
        List hourlyTemperature
        Plot hourlyTemperaturePlot
        Highchart hourlyTemperatureHighchart

        Map dailyBlock
        List dailyData
        List<List> minTemperatureDaily
        List<List> maxTemperatureDaily
        Plot minTemperaturePlot
        Plot maxTemperaturePlot
        Highchart dailyTemperatureHighchart

        StringBuilder tomorrowTemperature
        Plot tomorrowMinMaxTPlot
        Highchart tomorrowHighchart
        Plot tomorrowSummaryPlot

        StringBuilder todayTemperature
        Plot todayMinMaxTPlot
        Highchart todayHighchart
        Plot todaySummaryPlot

        long currentUpdateRate

        while (isRunning) {
            response = weatherUpdater.getForecast(COORDINATES)
            println response
            jsonParser = new JsonParser(response)

            //Update rate

            minutelyBlock = (Map) jsonParser.get("minutely")
            if (minutelyBlock) {
                minutelyData = (List) minutelyBlock.get("data")
                parameters = new ArrayList<Parameter>()
                minutelyData.each {
                    temp = (Map) it
                    parameters.add(new Parameter(Double.valueOf(temp.get("precipProbability").toString()),
                            Long.valueOf(temp.get("time").toString())))
                }

                parameters.each { println it }
                println "Update rate: "

                currentUpdateRate = UpdateRate.evaluate(parameters, 0.000001)
                if (currentUpdateRate < refreshRate) {
                    refreshRate = currentUpdateRate
                }
            }

            //now widget

            currentlyBlock = (Map) jsonParser.get("currently")

            //Current temperature
            currentTemperature = currentlyBlock.get("temperature").toString()
            celsius = Converter.toCelsius(Double.valueOf(currentTemperature)) + " ${Text.CELSIUS_SIGN}C"
            currentTemperaturePlot = new Plot(celsius, lineColor, true, [])
            //Current summary
            currentSummaryPlot = new Plot(currentlyBlock.get("summary").toString(), lineColor, true, [])

            nowHighchart = Highchart.createFromFile(getResource(NOW), Widgets.NOW, API_KEY)

            //hourly widget

            hourlyBlock = (Map) jsonParser.get("hourly")
            hourlyData = (List) hourlyBlock.get("data")
            hourlyTemperature = new ArrayList<Double>()

            hourlyData.each({
                temp = (Map) it
                hourlyTemperature.add([Text.formatTime(temp.get("time").toString(), TIME_FORMAT),
                                     Converter.toCelsius(Double.valueOf(temp.get("temperature").toString()))])
            })
            hourlyTemperaturePlot = new Plot("${Text.CELSIUS_SIGN}t", "#FFAF22", true, hourlyTemperature)
            hourlyTemperatureHighchart = Highchart.createFromFile(getResource(TEMPERATURE_HOURLY),
                    Widgets.TEMPERATURE_HOURLY, API_KEY)

            //daily widget

            dailyBlock = (Map) jsonParser.get("daily")
            dailyData = (List) dailyBlock.get("data")
            minTemperatureDaily = new ArrayList<List>()
            maxTemperatureDaily = new ArrayList<List>()

            dailyData.each({
                temp = (Map) it
                minTemperatureDaily.add([Text.formatTime(temp.get("time").toString(), TIME_FORMAT),
                                         Converter.toCelsius(Double.valueOf(temp.get("temperatureMin").toString()))])

                maxTemperatureDaily.add([Text.formatTime(temp.get("time").toString(), TIME_FORMAT),
                                         Converter.toCelsius(Double.valueOf(temp.get("temperatureMax").toString()))])
            })

            minTemperaturePlot = new Plot("Min ${Text.CELSIUS_SIGN}t", "#5874FF", true, minTemperatureDaily)
            maxTemperaturePlot = new Plot("Max ${Text.CELSIUS_SIGN}t", "#FF5858", true, maxTemperatureDaily)
            dailyTemperatureHighchart = Highchart.createFromFile(getResource(TEMPERATURE_DAILY),
                    Widgets.TEMPERATURE_DAILY, API_KEY)

            //today widget

            //Today min/max temperature
            todayTemperature = new StringBuilder()
            todayTemperature.append(minTemperatureDaily.get(0).get(1))
                    .append(" ")
                    .append(Text.CELSIUS_SIGN)
                    .append("C")
                    .append(" ... ")
                    .append(maxTemperatureDaily.get(0).get(1))
                    .append(" ")
                    .append(Text.CELSIUS_SIGN)
                    .append("C")

            todayMinMaxTPlot = new Plot(todayTemperature.toString(), lineColor, true, [])
            //Tomorrow summary
            temp = (Map) dailyData.get(0)
            todaySummaryPlot = new Plot(temp.get("summary").toString(), lineColor, true, [])
            todayHighchart = Highchart.createFromFile(getResource(TODAY),
                    Widgets.TODAY, API_KEY)

            //tomorrow widget

            //Tomorrow min/max temperature
            tomorrowTemperature = new StringBuilder()
            tomorrowTemperature.append(minTemperatureDaily.get(1).get(1))
                    .append(" ")
                    .append(Text.CELSIUS_SIGN)
                    .append("C")
                    .append(" ... ")
                    .append(maxTemperatureDaily.get(1).get(1))
                    .append(" ")
                    .append(Text.CELSIUS_SIGN)
                    .append("C")

            tomorrowMinMaxTPlot = new Plot(tomorrowTemperature.toString(), lineColor, true, [])
            //Tomorrow summary
            temp = (Map) dailyData.get(1)
            tomorrowSummaryPlot = new Plot(temp.get("summary").toString(), lineColor, true, [])
            tomorrowHighchart = Highchart.createFromFile(getResource(TOMORROW),
                    Widgets.TOMORROW, API_KEY)


            //post data to the server


            println "\nNow post:"
            println Http.post(nowHighchart.getRequestURL(),
                    HEADERS, nowHighchart.getConfig([currentTemperaturePlot, currentSummaryPlot]))

            println "\nToday post:"
            println Http.post(todayHighchart.getRequestURL(),
                    HEADERS, todayHighchart.getConfig([todayMinMaxTPlot, todaySummaryPlot]))

            println "\nTomorrow post:"
            println Http.post(tomorrowHighchart.getRequestURL(),
                    HEADERS, tomorrowHighchart.getConfig([tomorrowMinMaxTPlot, tomorrowSummaryPlot]))

            println "\nHourly post:"
            println Http.post(hourlyTemperatureHighchart.getRequestURL(), HEADERS,
                    hourlyTemperatureHighchart.getConfig([hourlyTemperaturePlot]))

            println "\nDaily post:"
            println Http.post(dailyTemperatureHighchart.getRequestURL(), HEADERS,
                    dailyTemperatureHighchart.getConfig([minTemperaturePlot, maxTemperaturePlot]))

            try {
                Thread.sleep(refreshRate)
            } catch (InterruptedException e) {
                e.printStackTrace()
            }
        }

    }

    /**
     * Stops the service.
     */
    synchronized void stop() {
        isRunning = false
    }

    private static String getResource(final String resource) {
        RESOURCES + resource
    }
}