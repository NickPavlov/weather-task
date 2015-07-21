import com.sysgears.weather_task.model.highcharts.Highchart
import com.sysgears.weather_task.model.highcharts.Plot
import com.sysgears.weather_task.model.http.Http

/**
 * Test.
 */
class Test {

    static final PATH = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/highchart.js"
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"
    static final WIDGET = "152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

    static void main(String[] args) {

        Highchart hchart = Highchart.createFromFile(PATH, WIDGET, API_KEY)
        Map headers = ["Content-Type": "application/json"]

        Plot newYork = new Plot("NewYork", "#108ec5", [17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5])
        Plot berlin = new Plot("Berlin", "#52b238", [13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0])
        Plot london = new Plot("London", "#ee5728", [17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5])

        hchart.updateData([newYork, berlin, london])
        println hchart.getConfig()

        println "Response status:"
        println Http.post(hchart.getRequestURL(), headers, hchart.getConfig())
    }
}