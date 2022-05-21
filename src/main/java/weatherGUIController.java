import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sun.util.resources.cldr.ext.CurrencyNames_en_SZ;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The controller of the GUI
 */
public class weatherGUIController {

    private boolean ifUseCachedData = false;
    private String weatherData = "";
    private Application application;
    private boolean overHeat = false;

    @FXML
    private TextField inputAPIKey;

    @FXML
    private TextField usZipCode;

    @FXML
    private Button getWeatherButton;

    @FXML
    private ChoiceBox<String> inputModePicker;

    @FXML
    private ChoiceBox<String> outputModePicker;

    @FXML
    private TextArea weatherDataField;

    @FXML
    private CheckBox useCachedData;

    @FXML
    private TextField outputApiKey;

    @FXML
    private TextField targetEmailAddress;

    @FXML
    private TextField targetName;

    @FXML
    private TextField subject;

    @FXML
    private TextField userEmailAddress;

    @FXML
    private TextField userName;

    @FXML
    private TextArea respondField;

    @FXML
    private Button sendEmailButton;

    @FXML
    private TextField thresholdInput;

    @FXML
    private Text very;

    @FXML
    private Text hot;

    @FXML
    private Text range;


    /**
     * initialize the application window and set default actions of certain components in the user interface
     */
    @FXML
    public void initialize() {
        inputModePicker.getItems().removeAll(inputModePicker.getItems());
        inputModePicker.getItems().addAll("Online", "Offline");

        outputModePicker.getItems().removeAll(outputModePicker.getItems());
        outputModePicker.getItems().addAll("Online", "Offline");
        inputModePicker.setOnAction(event -> {
            System.out.println(inputModePicker.getValue());
        });
        outputModePicker.setOnAction(event -> {
            System.out.println(outputModePicker.getValue());
        });
        useCachedData.setOnAction(event -> {
            System.out.println(useCachedData.isSelected());
            ifUseCachedData = useCachedData.isSelected();
        });

        getWeatherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startInputTask();
            }
        });

        sendEmailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startOutputTask();
            }
        });
    }

    /**
     * starts the input task, then put the task into another thread
     */
    public void startInputTask()
    {
        Runnable task = new Runnable()
        {
            public void run()
            {
                runInputTask();
            }
        };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    /**
     * get the info from the text field, then let the application to fetch weather data from API
     * updating text in GUI is delay to run later
     */
    public void runInputTask()
    {
        String inputAPIKeyText = inputAPIKey.getText();
        String usZipCodeText = usZipCode.getText();
        String inputMode = inputModePicker.getValue();
        String outputMode = outputModePicker.getValue();
        ifUseCachedData = useCachedData.isSelected();
        int threshold = Integer.parseInt(thresholdInput.getText());
//        usZipCodeText = "19019";
//        inputAPIKeyText = "fb6d55b649db4857b569c84977bad72b";

        if (threshold > 99 || threshold < -100) {
            range.setFill(Color.RED);
            overHeat = false;
        } else {
            range.setFill(Color.BLACK);
            hot.setVisible(false);
            very.setVisible(false);
            application = new Application(inputMode, outputMode, threshold);
            weatherData = application.getCurrentWeatherData(usZipCodeText, inputAPIKeyText, true);
            overHeat = application.isOverHeat();
        }

        // if the temp is higher than threshold the red text would appeared on the GUI
        if (overHeat){
            very.setVisible(true);
            hot.setVisible(true);
        }

        //to let the application update the text later
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                weatherDataField.setText(weatherData);
            }
        });
        System.out.println("input finished");
    }

    /**
     * starts the output task, then put the task into another thread
     */
    public void startOutputTask()
    {
        Runnable task = new Runnable()
        {
            public void run()
            {
                try {
                    runOutputTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    /**
     * get the weather info from the text field, then let the application to fetch weather data from API
     * updating text in GUI is delay to run later
     */
    public void runOutputTask() throws InterruptedException, IOException, URISyntaxException {

        String outputAPIKey = outputApiKey.getText();
        String targetEmailAddressText = targetEmailAddress.getText();
        String subjectText = subject.getText();
        String userEmailAddressText = userEmailAddress.getText();
        String userNameText = userName.getText();
        String targetNameText = targetName.getText();

//        outputAPIKey = "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";
//        targetEmailAddressText = "lzen9557@uni.sydney.edu.au";
//        subjectText = "subject";
//        userEmailAddressText = "lzen9557@uni.sydney.edu.au";
//        userNameText = "luis";
//        targetNameText = "lusss";

        String response = application.sendWeatherData(weatherData, outputAPIKey, targetEmailAddressText, targetNameText, subjectText, userEmailAddressText, userNameText);

        //to let the application update the text later
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                respondField.setText(response);
            }
        });
        System.out.println("Output finished");
    }

}
