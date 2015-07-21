package com.sysgears.weather_task.model.highcharts

/**
 * The <code>IHighchart</code> interface defines the behavior of the highchart object.
 */
interface IHighchart {

    /**
     * Should update "series" block with a new data.
     *
     * @param data new data
     */
    void updateData(final List<Plot> data);
}