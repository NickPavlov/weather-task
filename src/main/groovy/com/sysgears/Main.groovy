package com.sysgears

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
     * Main method.
     *
     * @param args
     */
    static void main(String[] args) {
        new Service(new DarkSkyForecast(API_KEY)).start()
    }
}