package com.sysgears.weather_task.model.utils

/**
 * The <code>Text</code> class provides functionality functionality for text decoration.
 */
class Text {

    /**
     *
     * @param unixTime
     * @param format
     * @return
     */
    static String formatTime(final String unixTime, final String format) {
        new Date(Long.valueOf(unixTime) * 1000).format(format)
    }

    /**
     *
     * @param pattern regex pattern
     * @param expression original string
     * @param replacement string
     * @return
     */
    static String replace(final String pattern, final String expression, final String replacement) {
        expression.replaceAll(pattern, replacement)
    }

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