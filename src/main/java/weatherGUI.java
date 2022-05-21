import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class weatherGUI extends Application {

    /**
     * starts the program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * starts the GUI window
     * @param primaryStage the primary stage
     * @throws IOException handle exception when no fxml file found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/weatherGUI.fxml"));
        primaryStage.setTitle("weather api");
        primaryStage.setScene(new Scene(root, 640, 600));
        primaryStage.show();
    }

    /**
     * the application clear its data base for later use
     */
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        DataManager dm = new DataManager();
        dm.cleanDB();
    }
}