
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * a Dummy output api
 */
public class DummyOutputAPIHandler implements OutputAPIHandler {

    /**
     * returns a example string and return a success code 202
     * @param weatherData
     * @param apiKey
     * @param toEmailAddress
     * @param receiverName
     * @param subject
     * @param fromEmailAddress
     * @param senderName
     * @return a response code
     */
    @Override
    public String sendWeatherData(String weatherData, String apiKey, String toEmailAddress, String receiverName,
                                String subject, String fromEmailAddress, String senderName) {

        if (apiKey.equals("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")){
            String jsonString = "{\"personalizations\":[{\"to\":[{\"email\":\"" + toEmailAddress + "\"," +
                    "\"name\":\"" + receiverName + "\"}],\"subject\":\"" + subject + "\"}],\"content\": [{\"type\": " +
                    "\"text/plain\", " +
                    "\"value\": " + weatherData + "}],\"from\":{\"email\":\"" + fromEmailAddress + "\"," +
                    "\"name\":\"" + senderName +
                    "\"}," +
                    "\"reply_to\":{\"email\":\"" + fromEmailAddress + "\",\"name\":\"" + senderName + "\"}}";

            Object obj = JSONValue.parse(jsonString);
            JSONObject map = (JSONObject)obj;
            return("202");
        } else {
            System.out.println("invalid output api key");
            return("invalid output api key");
        }

    }
}
