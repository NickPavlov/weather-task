package com.sysgears.model.weather

import com.sysgears.model.coordinates.ICoordinates

/**
 * The <code>DarkSkyForecast</code> class provides functionality to work with The Dark Sky Forecast service.
 */
class DarkSkyForecast implements IWeatherUpdater {

    /**
     * API link.
     */
    static final String FIRST_API = "https://api.forecast.io/forecast/"

    /**
     * Location.
     */
    final ICoordinates coordinates

    /**
     * Developer's personal key.
     */
    final String apiKey

    /**
     * The final link.
     */
    final URL url

    /**
     * Creates the <code>DarkSkyForecast</code> object specified by developer's personal key and the location.
     *
     * @param apiKey the developer's personal api key
     * @param coordinates the location for which the weather forecast is needed
     */
    DarkSkyForecast(final String apiKey, final ICoordinates coordinates) {
        this.apiKey = apiKey
        this.coordinates = coordinates
        this.url = new URL(FIRST_API + apiKey + "/" + coordinates)
    }

    /**
     * Returns the json response from the server.
     *
     * @return json
     */
    String update() {
        new BufferedReader(new InputStreamReader(url.openStream())).readLine()
    }
}