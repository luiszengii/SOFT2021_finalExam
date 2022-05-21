import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The main application of the software, act as a Model in MV pattern
 * Application handles the command passed from the GUI and call the corresponding method of its own APIs
 */
public class Application {

    private InputAPIHandler inputAPIHandler;
    private OutputAPIHandler outputAPIHandler;
    private DataManager dataManager;
    private int threshold;
    private String currentWeatherData;

    /**
     * Class Constructor, contains two APIs, an input API for getting weather information of a certain US state,
     * and a output one for sending the data to a email address.
     * @param firstArgument The type of input api
     * @param secondArgument The type of output api
     */
    public Application(String firstArgument, String secondArgument, int threshold){
        this.dataManager = new DataManager();
        this.threshold = threshold;
        this.currentWeatherData = null;
        if(firstArgument.equals("Online")){
            this.inputAPIHandler = new RealInputAPIHandler();
        } else if (firstArgument.equals("Offline")){
            this.inputAPIHandler = new DummyInputAPIHandler();
        }
        if(secondArgument.equals("Online")){
            this.outputAPIHandler = new RealOutputAPIHandler();
        } else if (secondArgument.equals("Offline")){
            this.outputAPIHandler = new DummyOutputAPIHandler();
        }
    }

    /**
     * this method would first check if the given zip code exist in the data base:
     *  if exist, it will ask the user if wish to store the cached data or obtain form the api;
     *  if to store the cached data, simply get the data and return;
     *  if to obtain from the api, get the data and updata the db.
     * @param USZipCode the zip code of the US state
     * @param apiKey A private api key provided by the user
     * @param useCachedData The choice of the user to use cached data or not
     * @return The String of the weather data in json form
     */
    public String getCurrentWeatherData(String USZipCode, String apiKey, boolean useCachedData){
        Integer usZipCode = Integer.parseInt(USZipCode);
        String result = this.dataManager.checkCache(usZipCode);
        if (result == null){
            // no record of the given data exist in the database, store the data from the api
            System.out.println("there is no " + usZipCode + "\'s information, proceed to store the data...");
            result = this.inputAPIHandler.getCurrentWeatherData(USZipCode, apiKey);
            this.dataManager.storeData(usZipCode, result);
        } else {
            System.out.println("the data of the given zip code: " + usZipCode + " is " + result);
            if(!useCachedData){
                //if user choose not to use cached data, first
                result = this.inputAPIHandler.getCurrentWeatherData(USZipCode, apiKey);
                this.dataManager.updateData(usZipCode, result);
                System.out.println("the updated data for "+ usZipCode +" is " + result);
            }
        }
        this.currentWeatherData = result;
        return result;
    }

    /**
     * Sends the given info to an email address through output api
     * @param weatherData The data part of the json String obtained from input api
     * @param apiKey
     * @param toEmailAddress
     * @param receiverName
     * @param subject
     * @param fromEmailAddress
     * @param senderName
     * @throws IOException Catch the exception that might occured in output api
     */
    public String sendWeatherData(String weatherData, String apiKey, String toEmailAddress, String receiverName,
                             String subject,
                                String fromEmailAddress, String senderName) throws IOException, URISyntaxException, InterruptedException {
        //if is over heat the report should start with an *
        String weatherDataSent = weatherData;
        if(this.isOverHeat()){
            weatherDataSent = "*" + weatherDataSent;
        }
        return this.outputAPIHandler.sendWeatherData(weatherDataSent, apiKey, toEmailAddress, receiverName, subject,
                fromEmailAddress, senderName);
    }

    /**
     * finds the temperature figure and compare it with the threshold
     * @return Returns if the temperature of the weather is higher than threshold
     */
    public boolean isOverHeat(){
        if (this.currentWeatherData != null){
            String temperature = this.currentWeatherData.substring(this.currentWeatherData.indexOf("temp") + 7,
                    this.currentWeatherData.indexOf("temp") + 17);
            String[] ls = temperature.split("\n");
            double temp = Double.parseDouble(ls[0]);
            double threshold = (double) this.threshold;
            return temp > threshold;
        } else {
            return false;
        }
    }

    public void setInputApi(InputAPIHandler inputApiHandler) {
        this.inputAPIHandler = inputApiHandler;
    }

    public void setOutputAPIHandler(OutputAPIHandler outputAPIHandler) {
        this.outputAPIHandler = outputAPIHandler;
    }

    public void showAllData(){
        this.dataManager.printAllData();
    }

    //test whether the code exists in db
    public boolean checkZipCode(String usZipcode) {
        return this.dataManager.checkCache(Integer.parseInt(usZipcode)) != null;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public String getCurrentWeatherDataVariable() {
        return this.currentWeatherData;
    }

    public void setCurrentWeatherData(String currentWeatherData) {
        this.currentWeatherData = currentWeatherData;
    }
}
