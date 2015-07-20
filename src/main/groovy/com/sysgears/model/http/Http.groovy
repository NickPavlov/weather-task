package com.sysgears.model.http

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder

/**
 * The <code>Http</code> class provides functionality to perform http requests.
 */
class Http {

    /**
     * Performs POST request.
     *
     * @param url http link
     * @param headers http message headers
     * @param body http message body
     */
    static void post(final String url, final Map<String, String> headers, final String body) {
        HttpPost request = new HttpPost(url)
        headers.each { request.addHeader(it.key, it.value) }
        request.setEntity(new StringEntity(body))
        HttpClientBuilder.create().build().execute(request);
    }

    private Http() {
    }
}