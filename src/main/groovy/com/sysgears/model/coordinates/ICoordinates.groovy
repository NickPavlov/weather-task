package com.sysgears.model.coordinates;

/**
 * The <code>ICoordinate</code> interface defines the behavior of the coordinate object.
 */
interface ICoordinates {

    /**
     * Should returns the latitude.
     *
     * @return
     */
    double getLatitude();

    /**
     * Should returns the longitude.
     *
     * @return
     */
    double getLongitude();
}