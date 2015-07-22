package com.sysgears.weather_task.model.highcharts

import com.sysgears.weather_task.model.file.TextFileReader
import com.sysgears.weather_task.model.highcharts.plot.Plot
import com.sysgears.weather_task.model.utils.Text
import groovy.json.JsonOutput

/**
 * The <code>Highchart</code> class provides functionality to work with a Geckoboard highchart diagrams.
 */
class Highchart implements IHighchart {

    /**
     * Geckoboard API link.
     */
    static final API_URL = "https://push.geckoboard.com/v1/send/"

    /**
     * Series block pattern.
     */
    static final SERIES_BLOCK = "(?<=series:\\[).*(?=\\])"

    /**
     * Widget key.
     */
    final String widgetKey

    /**
     * Original highchart configuration.
     */
    final String originalConfig

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
     * @param widget
     * @param api_key developer's personal api key
     * @return Highchart object
     */
    static Highchart createFromFile(final String path, final Widgets widget, final String apiKey) {
        //String highchartFile = new TextFileReader().getContent(path)
        //String postBody = JsonOutput.toJson([api_key: apiKey, data: [highchart: highchartFile.toString()]])

        new Highchart(widget.key, apiKey, new TextFileReader().getContent(path))
    }

    /**
     * Returns configuration string in parser format.
     *
     * @param data data which need to be included into the configuration string
     */
    String getConfig(final List<Plot> data) {
        StringBuilder newData = new StringBuilder()
        data.each { newData.append("${it.toString()},") }

        String highchart = Text.replace(SERIES_BLOCK, originalConfig, newData.toString())
        JsonOutput.toJson([api_key: apiKey, data: [highchart: highchart]])
    }

    /**
     * Creates the <code>Highchart</code> object specified by widget key,
     * JavaScript originalConfig, developer's personal key.
     *
     * @param widgetKey widget unique key
     * @param apiKey developer's personal key
     * @param config highchart originalConfig
     */
    private Highchart(final String widgetKey, final String apiKey, final String config) {
        this.widgetKey = widgetKey
        this.originalConfig = config
        this.apiKey = apiKey
        this.requestURL = API_URL + widgetKey
    }
}