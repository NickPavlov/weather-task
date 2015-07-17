package com.sysgears.model.weather

/**
 * The <code>DarkSkyForecast</code> class provides functionality to work with The Dark Sky Forecast service.
 */
class DarkSkyForecast implements IWeatherUpdater {

    /**
     * API link.
     */
    static final String FIRST_API = "https://api.forecast.io/forecast/"

    /**
     * Developer's personal key.
     */
    final String apiKey

    /**
     * Location.
     */
    ICoordinates coordinates

    /**
     * The final link.
     */
    URL url

    /**
     * Creates the <code>DarkSkyForecast</code> object specified by developer's personal key and the location.
     *
     * @param apiKey the developer's personal api key
     * @param coordinates the location for which the weather forecast is needed
     */
    DarkSkyForecast(final String apiKey, final ICoordinates coordinates) {
        this.apiKey = apiKey
        setLocation(coordinates)
    }

    /**
     * Creates the <code>DarkSkyForecast</code> object specified by developer's personal key.
     *
     * @param apiKey
     */
    DarkSkyForecast(final String apiKey) {
        this(apiKey, null)
    }

    /**
     * Returns the json response from the server.
     *
     * @return json
     */
    synchronized String update() {
        if (!coordinates) {
            throw new NullPointerException("Coordinates are not specified.")
        }

        new BufferedReader(new InputStreamReader(url.openStream())).readLine()
    }

    /**
     * Sets new coordinates.
     */
    synchronized void setLocation(final ICoordinates coordinates) {
        this.coordinates = coordinates
        this.url = currentUrl()
    }

    /**
     * Creates URL object.
     *
     * @return url
     */
    private URL currentUrl() {
        new URL(FIRST_API + apiKey + "/" + coordinates)
    }
}