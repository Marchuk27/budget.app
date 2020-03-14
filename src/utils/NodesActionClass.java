package utils;

import static constants.CategoriesTextForFiles.*;
import enumerations.Months;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NodesActionClass {

    public static void setDayPartPanesVisibleFalse(AnchorPane p1, AnchorPane p2, AnchorPane p3, AnchorPane p4) {
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
    }

    public static void setDefaultConditionForButtons(FlowPane treeViewPane, Button settingsButton, Button themeButton,
                                                     Button infoButton, Label deleteDiary, Label addDiary) {
        treeViewPane.setVisible(false);
        settingsButton.setLayoutY(218);
        themeButton.setLayoutY(299);
        infoButton.setLayoutY(380);
        deleteDiary.setVisible(false);
        addDiary.setVisible(false);
    }

    public static void setConditionForThemeButton(FlowPane treeViewPane, Button settingsButton, Button themeButton,
                                                     Button infoButton, Label deleteDiary, Label addDiary) {
        treeViewPane.setVisible(false);
        settingsButton.setLayoutY(218);
        themeButton.setLayoutY(299);
        infoButton.setLayoutY(480);
        deleteDiary.setVisible(false);
        addDiary.setVisible(false);
    }

    public static void setDefaultVisibleForCheckBox(Button acceptButton, CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
        acceptButton.setVisible(false);
        cb1.setVisible(false);
        cb2.setVisible(false);
        cb3.setVisible(false);
        cb4.setVisible(false);
    }

    public static void setDefaultConditionForCheckBox(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
        cb1.setSelected(false);
        cb2.setSelected(false);
        cb3.setSelected(false);
        cb4.setSelected(false);
    }

    public static void setDefaultVisibleForElements(FlowPane paneWithMonthButtons, AnchorPane paneWithCategories,
                                                    Label diaryLabel, Label workAreaLabel, Label backToMonth,
                                                    Label labelWithMonth) {
        paneWithMonthButtons.setVisible(false);
        paneWithCategories.setVisible(false);
        diaryLabel.setVisible(false);
        workAreaLabel.setVisible(false);
        backToMonth.setVisible(false);
        labelWithMonth.setVisible(false);
    }

    public static void setMonthsForYearButton(Button button, String path) throws IOException {
        File monthsDir = new File(path + button.getText());
        List<String> lines = new ArrayList<>();
        addCategoriesStringToList(lines);
        for (Months nameOfMonth : Months.values()) {
            File monthFile = new File(monthsDir + "//" + nameOfMonth.toString() + ".txt");
            boolean isMonthFileCreated = monthFile.createNewFile();
            if (isMonthFileCreated) {
                FileWriter writer = new FileWriter(monthFile);
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();
            }
        }
    }

    private static void addCategoriesStringToList(List<String> list) {
        list.add(SUPERMARKET_TXT);
        list.add(BEAUTY_HEALTH_TXT);
        list.add(HOUSE_REPAIR_TXT);
        list.add(TRANSPORT_TXT);
        list.add(CLOTHES_SHOES_TXT);
        list.add(ENTERTAINMENT_TXT);
        list.add(GIFT_TXT);
        list.add(STATE_SERVICE_TXT);
        list.add(ZHKU_TXT);
        list.add(ANOTH_COST_TXT);
        list.add(SALARY_TXT);
        list.add(BUSINESS_TXT);
        list.add(INVEST_TXT);
        list.add(DEPOSIT_TXT);
        list.add(SOCIAL_TXT);
        list.add(ANOTH_INCOME_TXT);
    }
}
