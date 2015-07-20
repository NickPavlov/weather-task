package com.sysgears.weather_task.model.weather

import com.sysgears.weather_task.model.coordinates.Coordinates

/**
 * The <code>IWeatherForecast</code> interface defines the behavior of the weather updater object.
 */
interface IWeatherForecast {

    /**
     * Should return the response from the weather server.
     *
     * @param coordinates the location for which the weather forecast is needed
     * @return response
     */
    def getForecast(final Coordinates coordinates)
}