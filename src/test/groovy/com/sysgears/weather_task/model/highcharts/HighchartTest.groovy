package com.sysgears.weather_task.model.highcharts

import com.sysgears.weather_task.model.highcharts.plot.Plot
import com.sysgears.weather_task.model.http.Http

/**
 * Highchart test.
 */
class HighchartTest {

    static final PATH = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/temperature.js"
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"
    static final WIDGET = "152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

    static void main(String[] args) {

        Highchart hchart = Highchart.createFromFile(PATH, WIDGET, API_KEY)
        Map headers = ["Content-Type": "application/json"]

        Plot newYork = new Plot("Temperature", "#ff0000", [17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5])
        //Plot berlin = new Plot("Berlin", "#00ff00", [13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0])
        //Plot london = new Plot("London", "#0000ff", [11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8])

        String data = hchart.getConfig([newYork])

        println data

        println "Response status:"
        println Http.post(hchart.getRequestURL(), headers, data)
    }
}