package com.sysgears.weather_task.model.highcharts

import groovy.json.JsonOutput

/**
 * The <code>Plot</code> class provides functionality to work with a highchart plot.
 */
class Plot {

    /**
     * Plot name
     */
    final String name

    /**
     * Plot color.
     */
    final String color

    /**
     * Plot data
     */
    final List data;

    /**
     * Creates the <code>Plot</code> object specified by name, color and data.
     *
     * @param name String
     * @param color String
     * @param data list of data
     */
    Plot(final String name, final String color, final List<Double> data) {
        this.name = name
        this.color = color
        this.data = data
    }

    /**
     * Returns String representation of the current object.
     *
     * @return String
     */
    @Override
    public String toString() {
        JsonOutput.toJson([name: name, data: data, color: color])
    }
}