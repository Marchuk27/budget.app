package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * Class for loading some settings needed at the start-point
 * 05.01.2020
 */

public class StartConfigurator {

    public static String determineStartPath() {
        File firstFile = new File(new File("").getAbsolutePath());
        File parentFile = firstFile.getParentFile().getParentFile();
        return parentFile.toString() + "/production/BudgetApp/files/";
//        return "C:/Users/aleks/Documents/BudgetApp/out/production/BudgetApp/files/";
    }

    public static void loadStartConfigFromTxt() {
        File path = new File(determineStartPath().substring(0, determineStartPath().length() - 6));
    }

    protected void getStyleSheetsForNodes(AnchorPane pane, Button b1, Button b2, Button b3, Button b4, AnchorPane
            pane2) {
        pane.getStylesheets().add(StartConfigurator.class.getResource("/css/ButtonStyles.css").toExternalForm());
        b1.getStyleClass().add("diaryButton");
        b2.getStyleClass().add("settingsButton");
        b3.getStyleClass().add("themeButton");
        b4.getStyleClass().add("infoButton");
        pane2.getStylesheets().add(StartConfigurator.class.getResource("/css/CategoriesStyle.css").toExternalForm());
    }

    protected void getStyleClassesForThemeButtons(Label b1, Label b2, Label b3) {
        b1.getStyleClass().add("defaultTheme");
        b2.getStyleClass().add("nauticalTheme");
        b3.getStyleClass().add("raspberryTheme");
    }

    protected void calculateTime(List<AnchorPane> greetingsPaneList) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay >= 5 && hourOfDay < 12) { //утро
            greetingsPaneList.get(0).setVisible(true);
        }
        if (hourOfDay > 11 && hourOfDay <= 16) { //день
            greetingsPaneList.get(1).setVisible(true);
        }
        if (hourOfDay > 16) { //вечер
            greetingsPaneList.get(2).setVisible(true);
        }
        if (hourOfDay >= 0 && hourOfDay <= 4) { //ночь
            greetingsPaneList.get(3).setVisible(true);
        }
    }

    protected void setEventHandlerForLogoView(ImageView logoView) {
        logoView.setImage(new Image("/resources/png_pics/logo.png"));
        logoView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img = new Image("/resources/png_pics/logogreen.png");
                logoView.setImage(img);
            }
        });
        logoView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img = new Image("/resources/png_pics/logo.png");
                logoView.setImage(img);
            }
        });
    }

    protected static void setEventHandlerForBlueLogoView(ImageView logoView) {
        logoView.setImage(new Image("/resources/png_pics/logo.png"));
        logoView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img = new Image("/resources/png_pics/logoblue.png");
                logoView.setImage(img);
            }
        });
        logoView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img = new Image("/resources/png_pics/logo.png");
                logoView.setImage(img);
            }
        });
    }

    protected static void setEventHandlerForRedLogoView(ImageView logoView) {
        logoView.setImage(new Image("/resources/png_pics/redlogo.png"));
        logoView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img = new Image("/resources/png_pics/logored.png");
                logoView.setImage(img);
            }
        });
        logoView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image img = new Image("/resources/png_pics/redlogo.png");
                logoView.setImage(img);
            }
        });
    }
 }
