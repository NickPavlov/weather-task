package com.sysgears.weather_task.model.weather.update

/**
 * UpdateRate test.
 */
class UpdateRateTest {

    /**
     * Permissible difference between the values.
     */
    static double DELTA = 0.1

    public static void main(String[] args) {

        //new Parameter(value, time)
        Parameter a = new Parameter(0.5, 10)
        Parameter b = new Parameter(0.7, 20)
        Parameter c = new Parameter(0.71, 30)
        Parameter d = new Parameter(0.72, 40)
        Parameter e = new Parameter(0.73, 50)

        def data = [a, b, c, d, e]

        println UpdateRate.evaluate(data, DELTA)
    }
}