package com.sysgears.weather_task.model.highcharts

import com.sysgears.weather_task.model.file.TextFileReader
import groovy.json.JsonOutput

/**
 * The <code>Highchart</code> class provides functionality to work with a Geckoboard highcharts diagrams.
 */
class Highchart implements IHighchart {

    /**
     * Geckoboard API link.
     */
    static final API_URL = "https://push.geckoboard.com/v1/send/"

    /**
     * Widget key.
     */
    final String widgetKey

    /**
     * Highchart configuration.
     */
    String config

    /**
     * Developer's personal api key.
     */
    final String apiKey

    /**
     * Link to update data on the widget.
     */
    final String requestURL;

    /**
     * Creates the <code>Highchart</code> object from file.
     *
     * @param path file path
     * @param api_key developer's personal api key
     * @return Highchart object
     */
    static Highchart createFromFile(final String path, final String widgetKey, final String apiKey) {
        String highchartFile = new TextFileReader().getContent(path)
        String postBody = JsonOutput.toJson([api_key: apiKey, data: [highchart: highchartFile.toString()]])

        new Highchart(widgetKey, apiKey, postBody)
    }

    /**
     * Updates data.
     *
     * @param new data
     */
    void updateData(final List<Plot> data) {
        String seriesPattern = "(?<=series:\\[).*(?=\\])"
        StringBuilder newData = new StringBuilder()
        data.each {
            newData.append("${data.toString()},")
        }
        config = config.replaceAll(seriesPattern, newData.toString())
    }

    /**
     * Creates the <code>Highchart</code> object specified by widget key,
     * JavaScript config, developer's personal key.
     *
     * @param widgetKey widget unique key
     * @param apiKey developer's personal key
     * @param config highchart config
     */
    private Highchart(final String widgetKey, final String apiKey, final String config) {
        this.widgetKey = widgetKey
        this.config = config
        this.apiKey = apiKey
        this.requestURL = API_URL + widgetKey
    }
}