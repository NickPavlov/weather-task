package com.sysgears.model.weather

import com.sysgears.model.coordinates.ICoordinates

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

    /**
     * Should set new location.
     *
     * @param coordinates
     */
    void setLocation(final ICoordinates coordinates)
}