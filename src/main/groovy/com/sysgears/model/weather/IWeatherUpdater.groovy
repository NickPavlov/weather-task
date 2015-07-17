package com.sysgears.model.weather

/**
 * The <code>IWeatherUpdater</code> interface defines the behavior of the weather updater object.
 */
interface IWeatherUpdater {

    /**
     * Should return the response from the weather server.
     *
     * @return response
     */
    def update()
}