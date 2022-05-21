import java.util.Map;

/**
 * an input api interface, takes an uszipcode and an apikey
 */
public interface InputAPIHandler {
    String getCurrentWeatherData(String argument, String apiKey);
}
