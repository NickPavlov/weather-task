package com.sysgears.weather_task.model.file

/**
 * The <code>TextFileReader</code> class provides functionality to read files from file system.
 */
class TextFileReader implements IFileReader {

    /**
     * Returns a content from a text file.
     *
     * @param address file address
     * @return text content
     */
    String getContent(final String address) {
        if (!address) {
            throw new IllegalArgumentException("File address cannot be empty.")
        }
        File file = new File(address);
        BufferedReader fileReader = new BufferedReader(new FileReader(file))
        StringBuilder content = new StringBuilder()
        String line;
        while ((line = fileReader.readLine()) != null) {
            content.append(line)
        }

        content.toString()
    }
}