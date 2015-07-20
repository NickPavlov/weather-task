package com.sysgears.weather_task.model.highcharts.js

/**
 * The <code>JSBlock</code> class provides functionality...
 */
class JSBlock {

    final String name;

    /**
     * Map of properties.
     */
    final Map<String, Object> properties;

    /**
     *
     * @param name block name
     * @param properties map
     */
    JSBlock(final String name, final Map<String, Object> properties) {
        this.name = name
        this.properties = properties
    }

    /**
     *
     * @return
     */
    String toString() {
        StringBuilder result = new StringBuilder();
        properties.each({
            result.append(JSText.getAsJSProperty(it.key, it.value))
        })

        "{${name}:${result.toString()}}"
    }
}