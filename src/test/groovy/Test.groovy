import com.sysgears.weather_task.model.coordinates.Coordinates
import com.sysgears.weather_task.model.http.Http

/**
 * Test.
 */
class Test {

    static void main(String[] args) {
        println Http.get("https://api.forecast.io/forecast/16142d05a3314de31b6fce9a83469c06/"
                + new Coordinates(37.8267, -122.423), null)
    }
}