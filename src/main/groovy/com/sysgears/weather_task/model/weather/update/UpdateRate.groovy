package com.sysgears.weather_task.model.weather.update

/**
 * The <code>UpdateRate</code> class provides functionality to calculate the update interval.
 */
class UpdateRate {

    /**
     * Returns time in milliseconds.
     *
     * @param data data which will be analyzed
     * @param allowableDifference the difference between adjacent elements
     * @return int
     */
    static int evaluate(final List<Parameter> data, final double allowableDifference) {
        int rate;

        Parameter temp;

        Iterator i = data.iterator()

        double currentValue;

        while (i.hasNext()) {
            currentValue = i.next().value
        }

        0
    }
}