package com.sysgears.weather_task.model.json

import groovy.json.JsonSlurper

/**
 * The <code>JsonParser</code> class provides functionality to work with JSON data.
 * Uses the JsonSlurper API to parse the json.
 */
class JsonParser implements IParser {

    /**
     * Parsed map.
     */
    final Map data

    /**
     * Parses map from the <code>data</code>.
     *
     * @param data
     * @return map
     */
    static Map parse(final String data) {
        if (!data) {
            throw new IllegalArgumentException("Weather data cannot be empty.")
        }

        (Map) new JsonSlurper().parseText(data)
    }

    /**
     * Creates the <code>JsonParser</code> object.
     *
     * @param data the data that need to be parsed
     */
    JsonParser(final String data) {
        this.data = parse(data)
    }

    /**
     * Returns object which corresponds to the <code>key</code>.
     *
     * @return parsed object
     */
    def get(final String key) {
        if (!key) {
            throw new IllegalArgumentException("Key cannot be empty.")
        }

        data.get(key)
    }
}