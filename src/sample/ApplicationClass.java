package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationClass extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));

            mainStage.setTitle("Budget Manager");
            mainStage.setScene(new Scene(root, 1006, 669));
            mainStage.setResizable(false);
            mainStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}