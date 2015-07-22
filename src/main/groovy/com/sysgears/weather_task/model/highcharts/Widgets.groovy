package com.sysgears.weather_task.model.highcharts

/**
 * The <code>Widgets</code> enum class stores the unique keys of the widgets.
 */
enum Widgets {

    TEMPERATURE_CURRENT("152712-ec0eeb2e-3033-4ae9-b165-1da703f0023a"),
    SUMMARY_CURRENT("152712-edb4a1e2-027e-45f7-b302-6e06fc4ac3a6"),

    TEMPERATURE_FORECAST("152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee")

    /**
     * Widget unique key.
     */
    final String key

    Widgets(final String key) {
        this.key = key
    }
}