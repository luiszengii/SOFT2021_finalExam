import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * The real output api handler that sends the request to the online api to send the email
 */
public class RealOutputAPIHandler implements OutputAPIHandler {
    /**
     * sends the post request to the send grid api and read the response
     * @param weatherData the weather data to be sent
     * @param apiKey private api key for output api
     * @param toEmailAddress email address
     * @param receiverName receiver name
     * @param subject subject of the email
     * @param fromEmailAddress sender's email address
     * @param senderName sender's name
     * @throws IOException to catch the exception that might happen during sending package procedure
     */
    @Override
    public String sendWeatherData(String weatherData, String apiKey, String toEmailAddress, String receiverName,
                                String subject, String fromEmailAddress, String senderName) throws IOException, URISyntaxException, InterruptedException {

        System.out.println("sending email in real api here!");
        weatherData = weatherData.replaceAll("\"", "");
        weatherData = weatherData.replaceAll("\n", " ");
        System.out.println(weatherData);

        String jsonString = "'{\"personalizations\":[{\"to\":[{\"email\":\"" + toEmailAddress + "\"," +
                "\"name\":\"" + receiverName + "\"}]," +
                "\"subject\":\"" + subject + "\"}],\"content\": [{\"type\": \"text/plain\", \"value\": \"" + weatherData +
                "\"}]," +
                "\"from\":{\"email\":\"" + toEmailAddress + "\",\"name\":\"" + senderName + "\"}," +
                "\"reply_to\":{\"email\":\"" + toEmailAddress + "\",\"name\":\"" + senderName + "\"}}'";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.sendgrid.com/v3/mail/send"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
        return(Integer.toString(response.statusCode()));
    }

}
