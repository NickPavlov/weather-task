package com.sysgears.weather_task.model.js

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
     * @param expression
     * @return
     */
    static String escapeQuotes(final Object expression) {
        (expression.getClass() == String) ? addQuotes((String) expression) : expression
    }

    /**
     *
     * @param list
     * @return
     */
    static String printList(final List list) {
        StringBuilder result = new StringBuilder()
        result.append("[")
        list.each({
            result.append(escapeQuotes(it))
            result.append(",")
        })
        result.append("]")

        result.toString()
    }

    /**
     *
     * @param map
     * @return
     */
    static String printMap(final Map map) {
        StringBuilder result = new StringBuilder()
        result.append("{")
        map.each({
            result.append("${it.key}:${escapeQuotes(it.value)}")
            result.append(",")
        })
        result.append("}")

        result.toString()
    }
}