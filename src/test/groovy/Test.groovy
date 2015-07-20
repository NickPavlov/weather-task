import com.sysgears.weather_task.model.http.Http

/**
 * Test.
 */
class Test {

    static void main(String[] args) {


        BufferedReader file = new BufferedReader(new FileReader("/home/nick/Documents/highchart.txt"))
        StringBuilder script = new StringBuilder()
        String line = ""
        while ((line = file.readLine()) != null) {
            script.append(line + "\n")
        }

        println script.toString()


        //Highchart
        String url = "https://push.geckoboard.com/v1/send/152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

        //Geck-o-meter
        //String url = "https://push.geckoboard.com/v1/send/152712-7e44ff8d-e45d-4ca5-a4dc-777166c855ab"

        String windSpeedBody = "{\"api_key\":\"c143d855c29d5fe59d2ce0830c834e04\"," +
                "\"data\":{${script.toString()}}   }"


        println windSpeedBody

        Map<String, String> headers = new HashMap<String, String>()
        //headers.put("Content-Type", "application/json")

        println "Response code: " + Http.post(url, headers, windSpeedBody)


    }
}