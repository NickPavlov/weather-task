package com.sysgears.weather_task.model.file

/**
 * The <code>IFileReader</code> interface defines the behavior of the file object.
 */
interface IFileReader {

    /**
     * Should read a file form a specified address.
     *
     * @param address file address
     * @return content
     */
    def getContent(final String address);
}