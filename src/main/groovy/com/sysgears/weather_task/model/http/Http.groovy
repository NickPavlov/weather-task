package com.sysgears.weather_task.model.http

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder

/**
 * The <code>Http</code> class provides functionality to perform http requests.
 */
class Http {

    /**
     * Performs GET request.
     *
     * @param url http link
     * @param headers http message headers
     * @return http response
     */
    static String get(final String url, final Map<String, String> headers) {
        HttpGet request = new HttpGet(url)
        if (headers) {
            headers.each { request.addHeader(it.key, it.value) }
        }
        HttpResponse response = HttpClientBuilder.create().build().execute(request)
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
        StringBuilder result = new StringBuilder()
        String line = ""
        while ((line = rd.readLine()) != null) {
            result.append(line)
        }

        result.toString()
    }

    /**
     * Performs POST request.
     *
     * @param url http link
     * @param headers http message headers
     * @param body http message body
     * @return status code
     */
    static int post(final String url, final Map<String, String> headers, final String body) {
        HttpPost request = new HttpPost(url)
        if (headers) {
            headers.each { request.addHeader(it.key, it.value) }
        }
        request.setEntity(new StringEntity(body))

        HttpClientBuilder.create().build().execute(request).getStatusLine().statusCode;
    }

    private Http() {
    }
}