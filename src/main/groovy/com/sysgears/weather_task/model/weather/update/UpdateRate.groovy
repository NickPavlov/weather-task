package com.sysgears.weather_task.model.weather.update

/**
 * The <code>UpdateRate</code> class provides functionality to calculate the update interval.
 */
class UpdateRate {

    /**
     * Returns update rate time, time units are the same as in the parameter list.
     * The items in the list should be presented in chronological order.
     * In the case of a single parameter in the list, 0 will be returned.
     * Note, that the units of measure for all parameters should be given to the same type.
     *
     * @param data data which will be analyzed
     * @param delta the difference between adjacent elements
     * @return update rate time
     */
    static long evaluate(final List<Parameter> data, final double delta) {
        if (!data) {
            throw new IllegalArgumentException("Data cannot be empty.")
        }

        long rate = Math.abs(data.get(0).time - data.get(data.size() - 1).time)
        long currentRate
        Parameter startElement

        for (int i = 0; i < (data.size() -1); ++i) {
            if (Math.abs(data.get(i).value - data.get(i + 1).value) >= delta) {
                if (startElement) {
                    currentRate = Math.abs(startElement.time - data.get(i).time)
                    rate = currentRate < rate ? currentRate : rate
                    startElement = null
                } else {
                    startElement = data.get(i)
                }
            }
        }
        if (startElement) {
            rate = Math.abs(startElement.time - data.get(data.size() - 1).time)
        }

        return rate
    }
}