package com.sysgears.weather_task.model.highcharts.plot

import groovy.json.JsonOutput

/**
 * The <code>Plot</code> class provides functionality to work with a highchart charts.
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
     * Visibility.
     */
    final boolean visible

    /**
     * Plot data
     */
    final List data;

    /**
     * Creates the <code>Plot</code> object specified by name, color and data.
     *
     * @param name String
     * @param color String
     * @param visible visibility
     * @param data list of data
     */
    Plot(final String name, final String color, final boolean visible, final List data) {
        this.name = name
        this.color = color
        this.data = data
        this.visible = visible
    }

    /**
     * Returns String representation of the current object.
     *
     * @return String
     */
    @Override
    public String toString() {
        JsonOutput.toJson([name: name, data: data, color: color, visible: visible])
    }
}