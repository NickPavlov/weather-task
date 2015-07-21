package com.sysgears.weather_task.model.highcharts

import com.sysgears.weather_task.model.highcharts.plot.Plot

/**
 * The <code>IHighchart</code> interface defines the behavior of the highchart object.
 */
interface IHighchart {

    /**
     * Should return highchart chart configuration.
     * <code>data</code> represents elements of "series" block.
     *
     * @param data data which need to be included into the configuration string
     */
    String getConfig(final List<Plot> data);
}