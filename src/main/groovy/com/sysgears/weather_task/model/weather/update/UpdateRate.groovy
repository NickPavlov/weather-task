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
    static int evaluate(final List<Parameter> data, final double delta) {
        int rate = Math.abs(data.get(0).time - data.get(data.size() - 1).time)
        int temp
        Parameter startElement
        Parameter currentElement
        Iterator i = data.iterator()
        while (i.hasNext()) {
            currentElement = i.next()
            if (i.hasNext() && (Math.abs(currentElement.value - i.next().value) > delta)) {
                if (startElement) {
                    temp = Math.abs(startElement.time - currentElement.time)
                    rate = temp < rate ? temp : rate
                    startElement = null
                } else {
                    startElement = currentElement
                }
            }
        }

        return rate
    }
}