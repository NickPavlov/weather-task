package com.sysgears.weather_task.model.coordinates

/**
 * The <code>Coordinate</code> class provides functionality to work with coordinates.
 */
class Coordinates {

    /**
     * The latitude.
     */
    private double latitude;

    /**
     * The longitude.
     */
    private double longitude;

    /**
     * Creates the <code>Coordinates</code> object specified by latitude and longitude.
     *
     * @param latitude latitude
     * @param longitude longitude
     */
    Coordinates(final double latitude, final double longitude) {
        this.latitude = latitude
        this.longitude = longitude
    }

    /**
     * Returns the latitude parameter.
     *
     * @return latitude
     */
    double getLatitude() {
        return latitude
    }

    /**
     * Returns the longitude parameter.
     *
     * @return longitude
     */
    double getLongitude() {
        return longitude
    }

    /**
     * Returns String representation of the current object
     * in a format "latitude,longitude".
     *
     * @return String
     */
    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}