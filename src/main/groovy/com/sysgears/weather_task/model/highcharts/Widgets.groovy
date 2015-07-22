package com.sysgears.weather_task.model.highcharts

/**
 * The <code>Widgets</code> enum class stores the unique keys of the widgets.
 */
enum Widgets {

    /**
     * Now widget.
     */
    NOW("152712-edb4a1e2-027e-45f7-b302-6e06fc4ac3a6"),

    /**
     * Today widget.
     */
    TODAY("152712-e6590eff-7d41-4eb1-aa5c-643ed30bd0a3"),

    /**
     * Tomorrow widget.
     */
    TOMORROW("152712-3a2b491b-2c24-4951-854c-2fb8ae195984"),

    /**
     * Hourly temperature widget.
     */
    TEMPERATURE_HOURLY("152712-4ea7c95d-2f0d-49cc-8a08-59a6658b64ee"),

    /**
     * Daily temperature widget.
     */
    TEMPERATURE_DAILY("152712-f8137afd-4622-43bf-8061-8d2e9f34b3cb"),

    /**
     * Daily precipitation widget.
     */
    PRECIPITATION_DAILY("152712-44a78de8-8a78-40fd-8a86-bb57afe4e65e")

    /**
     * Widget unique key.
     */
    final String key

    /**
     * @param key unique widget
     */
    Widgets(final String key) {
        this.key = key
    }
}