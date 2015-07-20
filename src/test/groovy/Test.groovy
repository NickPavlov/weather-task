import com.sysgears.weather_task.model.highcharts.js.JSBlock

/**
 * Test.
 */
class Test {

    static void main(String[] args) {

        String key = "c143d855c29d5fe59d2ce0830c834e04"

        //Highchart
        String url = "https://push.geckoboard.com/v1/send/152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"

        //Geck-o-meter
        //String url = "https://push.geckoboard.com/v1/send/152712-7e44ff8d-e45d-4ca5-a4dc-777166c855ab"
/*

        def json = JsonOutput.toJson([api_key: key, age: 42])

        String windSpeedBody = "{\"api_key\":\"${key}\"," +
                "\"data\":{\"highchart\": " +
                "\"{" +
                "     chart: {type:\\\"area\\\"}," +
                "     title: {text:\\\"Area chart with negative values\\\"}," +
                "     xAxis: {categories: [\\\"Apples\\\", \\\"Oranges\\\"]}," +
                "     series: [{name: \\\"John\\\", data: [5, 3, 4, 7, 2]}]" +
                "}\"}   }"



            String windSpeedBody = "{\"api_key\" : \"${key}\"," +
                    "\"data\" : {   ${data.toString()}  }}"



            println windSpeedBody

        Map<String, String> headers = new HashMap<String, String>()
        headers.put("Content-Type", "application/json")

        println "Response code: " + Http.post(url, headers, windSpeedBody)
        */

        List list = ["1", "2", "3"]
        Map map = new HashMap()
        map.put("list", list)
         println new JSBlock("block", map)


    }
}