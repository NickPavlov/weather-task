package com.sysgears.weather_task.model.highcharts

import com.sysgears.weather_task.model.file.TextFileReader
import groovy.json.JsonOutput

/**
 * The <code>Highchart</code> class provides functionality to work with a highcharts diagrams.
 */
class Highchart {

    /**
     * Widget key.
     */
    final String widgetKey;

    /**
     * Highchart configuration.
     */
    final String config;

    /**
     * Developer's personal api key.
     */
    final String apiKey;

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
     * Updates series data.
     *
     * @param series new data
     */
    void updateSeries(final List series) {

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
    }
}