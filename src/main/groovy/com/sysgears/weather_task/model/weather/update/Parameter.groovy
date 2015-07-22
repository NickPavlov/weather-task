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
    final long time

    /**
     * Creates the <code>Parameter</code> object specified by value and time.
     *
     * @param value double
     * @param time long
     */
    Parameter(final double value, final long time) {
        this.value = value
        this.time = time
    }

    /**
     * Returns String representation of the current object.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[${value},${time}]"
    }
}