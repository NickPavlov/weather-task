package com.sysgears
import com.sysgears.model.coordinates.Coordinates
import com.sysgears.model.coordinates.ICoordinates
import com.sysgears.model.weather.DarkSkyForecast

/**
 * Main class.
 */
class Main {

    /**
     * Developer's personal key
     */
    static final String API_KEY = "16142d05a3314de31b6fce9a83469c06"

    /**
     * Location coordinates.
     */
    static final ICoordinates COORDINATES = new Coordinates(37.8267, -122.423)

    /**
     * Main method.
     *
     * @param args
     */
    static void main(String[] args) {
        new Service(new DarkSkyForecast(API_KEY, COORDINATES)).start()
    }
}