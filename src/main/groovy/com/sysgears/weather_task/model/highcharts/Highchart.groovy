package com.sysgears.weather_task.model.highcharts

/**
 *
 */
class Highchart {

    /**
     *
     */
    static final String LINE = "line"

    /**
     *
     */
    private static final String MAINBLOCK = "highchart"

    /**
     *
     */
    final String text;


    Highchart(final String type,
              final String title,
              final List<String> xAxis,
              final List<String> yAxis) {

        this.text = ""

    }
}