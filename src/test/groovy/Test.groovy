import com.sysgears.weather_task.model.file.TextFileReader
import com.sysgears.weather_task.model.http.Http
import groovy.json.JsonOutput

/**
 * Test.
 */
class Test {

    static final PATH = "/home/nick/IdeaProjects/Weather_Task/src/main/resources/highchart.js"
    static final API_KEY = "c143d855c29d5fe59d2ce0830c834e04"
    static final URL = "https://push.geckoboard.com/v1/send/"
    static final WIDGET = "152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

    static void main(String[] args) {

        String jsFile = new TextFileReader().getContent(PATH)

        //String temp = StringEscapeUtils.escapeJavaScript(jsFile.toString())
        String postBody = JsonOutput.toJson([api_key: API_KEY, data: [highchart: jsFile.toString()]])
        println postBody

        /*
        Map<String, String> headers = new HashMap<String, String>()
        headers.put("Content-Type", "application/json")
        */

        println "Response code: " + Http.post(URL + WIDGET, ["Content-Type": "application/json"], postBody)
    }
}