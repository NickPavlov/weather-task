import com.sysgears.weather_task.model.highcharts.Highchart
import com.sysgears.weather_task.model.http.Http

/**
 * Test.
 */
class Test {

    static final PATH = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/highchart.js"
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"
    static final URL = "https://push.geckoboard.com/v1/send/"
    static final WIDGET = "152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

    static void main(String[] args) {

        Highchart hchart = Highchart.createFromFile(PATH, WIDGET, API_KEY)
        Map headers = ["Content-Type": "application/json"]

        println "Response status:"
        println Http.post(URL + WIDGET, headers, hchart.getConfig())
    }
}