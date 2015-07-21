package com.sysgears.weather_task.model.utils

/**
 * The <code>Text</code> class provides functionality functionality for text decoration.
 */
class Text {

    /**
     * Prints map in curly brackets.
     *
     * @param map map
     * @return String representation of the map
     */
    static String printMap(final Map map) {
        StringBuilder result = new StringBuilder()
        result.append("{")
        map.each({
            result.append("${it.key}:${it.value},")
        })
        result.append("}")

        result.toString()
    }
}