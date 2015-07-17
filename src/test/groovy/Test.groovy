import com.sysgears.model.http.Http

/**
 * Test.
 */
class Test {

    static void main(String[] args) {


        String url = "https://push.geckoboard.com/v1/send/152712-ecb527c2-540e-4894-97db-23f5cc6f1d9b"

        String body = "{\"api_key\":\"c143d855c29d5fe59d2ce0830c834e04\"," +
                "\"data\":{\"item\":20,\"min\":{\"value\":0},\"max\":{\"value\":100}}}"

        Map<String, String> headers = new HashMap<String, String>()
        headers.put("Content-Type", "application/json")

        Http.post(url, headers, body)

    }
}