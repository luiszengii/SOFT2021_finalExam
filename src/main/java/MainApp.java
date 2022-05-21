import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * the Main app act as a CLI, which handles user's input and present data from application back to user
 */
public class MainApp {

    /**
     * runs the CLI and starts the application
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        //to run with argument, type gradle run --args="offline online"
        Application application = new Application(args[0], args[1], 0);
        String weatherData;

        //taking input from users
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter us zip code:");
        String USZipcode = sc.nextLine();
        System.out.println("Enter your input api key:");
        String inputApiKey = sc.nextLine();
        System.out.println("Use Cached Data?[Y/N]");
        boolean useCachedData = sc.nextLine().contains("Y");

        //an example
        USZipcode = "28547";
        inputApiKey = "fb6d55b649db4857b569c84977bad72b";

        //first obtain weather data from input api, than use the returned json string to send data to output api
        weatherData = application.getCurrentWeatherData(USZipcode, inputApiKey, useCachedData);

        //output api key: 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
        String outputApiKey = "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";
        String toEmailAddress = "lzen9557@uni.sydney.edu.au";
        String receiverName = "luis";
        String subject = "none";
        String fromEmailAddress = "lzen9557@uni.sydney.edu.au";
        String senderName = "luis";

        application.sendWeatherData(weatherData, outputApiKey, toEmailAddress, receiverName, subject, fromEmailAddress, senderName);
    }

}
