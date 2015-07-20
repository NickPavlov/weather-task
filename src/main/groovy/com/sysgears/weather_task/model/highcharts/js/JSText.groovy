package com.sysgears.weather_task.model.highcharts.js

/**
 *
 */
class JSText {

    /**
     *
     * @param expression
     * @return
     */
    static String addQuotes(final String expression) {
        "\\\"${expression}\\\""
    }

    /**
     *
     * @param name property name
     * @param value property value
     * @return
     */
    static String getJSProperty(final String name, final Object value) {
        if (value.getClass() == String) {
            "{${name}:${addQuotes((String) value)}}"
        } else {
            "{${name}:${value}}"
        }

    }
}