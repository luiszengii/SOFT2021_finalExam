import java.io.IOException;
import java.net.URISyntaxException;

/**
 * output api interface
 */
public interface OutputAPIHandler {
    String sendWeatherData(String weatherData, String apiKey, String toEmailAddress, String receiverName,
                           String subject,
                         String fromEmailAddress, String senderName) throws IOException, URISyntaxException, InterruptedException;
}
