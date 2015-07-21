package com.sysgears.weather_task
import com.sysgears.weather_task.model.coordinates.Coordinates
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

    }
}