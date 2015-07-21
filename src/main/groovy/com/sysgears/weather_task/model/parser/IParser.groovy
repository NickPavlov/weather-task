package com.sysgears.weather_task.model.parser
/**
 * The <code>IParser</code> interface defines the behavior of the parser object.
 */
interface IParser {

    /**
     * Should return an object which corresponds to the <code>key</code>.
     *
     * @param key
     * @return corresponding object
     */
    def get(final String key)
}