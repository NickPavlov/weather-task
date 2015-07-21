package com.sysgears.weather_task.model.utils

/**
 * The <code>Converter</code> class provides functionality...
 */
class Converter {

    /**
     * Accuracy.
     */
    static final int accuracy = 1000;

    /**
     * Converts temperature from fahrenheit to celsius.
     *
     * @param fahrenheit double
     * @param decimalCount count of decimals signs
     * @return double
     */
    static double toCelsius(final double fahrenheit) {
        Math.round((5/9) * (fahrenheit - 32) * accuracy) / accuracy
    }
}