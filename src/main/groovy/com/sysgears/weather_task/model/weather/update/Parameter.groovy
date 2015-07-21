package com.sysgears.weather_task.model.weather.update

/**
 * The <code>Parameter</code> class provides functionality...
 */
class Parameter {

    /**
     * Parameter value.
     */
    final double value

    /**
     * Unix time.
     */
    final int time

    /**
     * Creates the <code>Parameter</code> object specified by value and time.
     *
     * @param value double
     * @param time int
     */
    Parameter(final double value, final int time) {
        this.value = value
        this.time = time
    }
}