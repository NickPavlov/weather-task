package com.sysgears.weather_task
import com.sysgears.weather_task.model.coordinates.Coordinates
import com.sysgears.weather_task.model.parser.IParser
import com.sysgears.weather_task.model.parser.JsonParser
import com.sysgears.weather_task.model.weather.IWeatherForecast
/**
 * The <code>Service</code> class combines logic of the main components.
 */
class Service {

    static final PATH = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/highchart.js"
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"
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
        //println "Response:\n${response}"

        Map hourly = (Map) jsonParser.get("hourly")
        List data = (List) hourly.get("data")

        List<Double> temperature = new ArrayList<Double>()
        Map temp;
        data.each({
            println it
            //temp = (Map) it
            //temperature.add(Double.valueOf(temp.get("temperature").toString()))
        })

        /*
        Plot t = new Plot("temperature", "#ff0000", temperature)
        Highchart hchart = Highchart.createFromFile(PATH, WIDGET, API_KEY)
        Map headers = ["Content-Type": "application/json"]
        String config = hchart.getConfig([t])
        //println config
        println Http.post(hchart.getRequestURL(), headers, config)

*/
        //println "\n\nCurrently\n${jsonParser.get("currently")}\n"
        //println "WindSpeed: ${data.get("windSpeed")}"

    }
}