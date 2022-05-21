import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import org.json.simple.*;

/**
 * The real input api handler that sends the request to the online api and reads the response
 */
public class RealInputAPIHandler implements InputAPIHandler {

    /**
     * sends the request and get the weather data
     * @param USZipCode the us zip code of the state
     * @param apiKey an private apikey
     * @return the json string of the weather
     */
    @Override
    public String getCurrentWeatherData(String USZipCode, String apiKey) {
        System.out.println("getting weather data from read api here");
        JSONObject dataJsonMap = null;
        String weatherData = null;

        try{
            //visit the weather api with given us zip code and api key, by sending a GET request with header info
            String targetURL = "https://api.weatherbit.io/v2.0/current?postal_code=" + USZipCode + "&key=" + apiKey;
            URL urlForGetRequest = new URL(targetURL);
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // if the request is not success throw an exception
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);// a one line string
            String output = br.readLine();

            // convert JSON string to Map(JSONObject)
            Object obj = JSONValue.parse(output);
            JSONObject jsonMap = (JSONObject)obj;
            JSONArray dataArray = (JSONArray) jsonMap.get("data");
            dataJsonMap = (JSONObject) dataArray.get(0);

            //parse the "data" section to a linked hash map to be human readable
            System.out.println(dataJsonMap.toJSONString());
            System.out.println("THE CURRENT WEATHER DATA FOR " + dataJsonMap.get("city_name") + " IS:\n");
            weatherData = "The current weather data for " + dataJsonMap.get("city_name") + " is:\n";
            for (Object key : dataJsonMap.keySet()){
                weatherData += key.toString() + ": " + dataJsonMap.get(key).toString() + "\n";
            }


            /*now get the severe weather alert of the city, by looking at the coordination */
            // send get request to alert api
            String lat = dataJsonMap.get("lat").toString();
            String lon = dataJsonMap.get("lon").toString();
            targetURL = "https://api.weatherbit.io/v2.0/alerts?lat=" + lat + "&lon=" + lon + "&key=" + apiKey;
            urlForGetRequest = new URL(targetURL);
            connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // if the request is not success throw an exception
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
            }
            in = new InputStreamReader(connection.getInputStream());
            br = new BufferedReader(in);// a one line string
            output = br.readLine();

            //parse the json file to alerts strings
            obj = JSONValue.parse(output);
            JSONObject alertMap = (JSONObject)obj;
            JSONArray alertArray = (JSONArray) alertMap.get("alerts");
            if (alertArray.size() == 0){
                System.out.println("no alerts in the area");
                weatherData += "no alerts in the area";
            } else {
                JSONObject alertsMap = (JSONObject) alertArray.get(0);
                System.out.println("\n*ALERTS*: \n");
                weatherData += "\n*ALERTS*: \n";
                alertsMap.forEach((key, value) -> System.out.println(key + ":" + value.toString()));
                for (Object key : alertMap.keySet()){
                    weatherData += key.toString() + ": " + alertMap.get(key).toString() + "\n";
                }
            }

            connection.disconnect();

        } catch (Exception e){
            System.out.println("exception: " + e);
        }

        return weatherData;
    }
}
