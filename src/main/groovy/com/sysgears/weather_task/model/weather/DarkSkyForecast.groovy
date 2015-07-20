package com.sysgears.weather_task.model.weather

import com.sysgears.weather_task.model.coordinates.Coordinates
import com.sysgears.weather_task.model.http.Http

/**
 * The <code>DarkSkyForecast</code> class provides functionality to work with The Dark Sky Forecast service.
 */
class DarkSkyForecast implements IWeatherForecast {

    /**
     * API link.
     */
    static final String FIRST_API = "https://api.forecast.io/forecast/"

    /**
     * Developer's personal key.
     */
    final String apiKey

    /**
     * Creates the <code>DarkSkyForecast</code> object specified by developer's personal key and the location.
     *
     * @param apiKey the developer's personal api key
     */
    DarkSkyForecast(final String apiKey) {
        this.apiKey = apiKey
    }

    /**
     * Returns weather forecast from the server in json format.
     *
     * @param coordinates the location for which the weather forecast is needed
     * @return weather forecast in json format
     */
    String getForecast(final Coordinates coordinates) {
        if (!coordinates) {
            throw new NullPointerException("Coordinates are not specified.")
        }

        Http.get(currentUrl(coordinates), null)
    }

    /**
     * Creates URL object.
     *
     * @param coordinates the location for which the weather forecast is needed
     * @return url
     */
    private String currentUrl(final Coordinates coordinates) {
        FIRST_API + apiKey + "/" + coordinates
    }
}