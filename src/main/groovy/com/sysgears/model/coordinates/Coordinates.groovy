package com.sysgears.model.coordinates

/**
 * The <code>Coordinate</code> class provides functionality to work with coordinates.
 */
class Coordinates implements ICoordinates {

    /**
     * The latitude.
     */
    private double latitude;

    /**
     * The longitude.
     */
    private double longitude;

    /**
     *
     * @param latitude
     * @param longitude
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
     *
     * @return
     */
    @Override
    public String toString() {
        return latitude + "," +longitude;
    }
}