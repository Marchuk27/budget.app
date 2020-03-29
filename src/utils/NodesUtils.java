package utils;

import categories.AbstractCategory;
import enumerations.Months;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lombok.Cleanup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NodesUtils {

    public static List<AnchorPane> createGreetingPanesList(AnchorPane p1, AnchorPane p2, AnchorPane p3, AnchorPane p4) {
        List<AnchorPane> greetingList = new ArrayList<>();
        greetingList.add(p1);
        greetingList.add(p2);
        greetingList.add(p3);
        greetingList.add(p4);
        return greetingList;
    }

    public static void setDayPartPanesVisibleFalse(List<AnchorPane> greetingPanesList) {
        greetingPanesList.forEach(pane -> pane.setVisible(false));
    }

    public static void setFirstRectangleTrueVisible(Rectangle g1, Rectangle g2, Rectangle g3, Rectangle g4) {
        g1.setVisible(true);
        g2.setVisible(false);
        g3.setVisible(false);
        g4.setVisible(false);
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

    public static void addStyleClassForEventHistoryLabels(Label showHistory, Label hideHistory) {
        showHistory.getStyleClass().add("historyLabel");
        hideHistory.getStyleClass().add("historyLabel");
    }

    public static void addStyleClassForSideMenuButtons(Button showReport, Button hideReport) {
        showReport.getStyleClass().add("monthReportButton");
        hideReport.getStyleClass().add("monthReportButton");
    }

    public static List<CheckBox> createCheckBoxListForDelete(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
        return new ArrayList<>(Arrays.asList(cb1, cb2, cb3, cb4));
    }

    public static void setDefaultVisibleForCheckBox(Button acceptButton, List<CheckBox> checkBoxList) {
        acceptButton.setVisible(false);
        checkBoxList.forEach(cb -> cb.setVisible(false));
    }

    public static void setDefaultConditionForCheckBox(List<CheckBox> checkBoxList) {
        checkBoxList.forEach(cb -> cb.setSelected(false));
    }

    public static void setDefaultVisibleForElements(FlowPane paneWithMonthButtons, AnchorPane paneWithCategories,
                                                    Label diaryLabel, Label workAreaLabel, Label backToMonth) {
        paneWithMonthButtons.setVisible(false);
        paneWithCategories.setVisible(false);
        diaryLabel.setVisible(false);
        workAreaLabel.setVisible(false);
        backToMonth.setVisible(false);
    }

    public static void setParamsForWorkAreaMonthAndDiaryLabel(Label workAreaMonthAndDiaryLabel, Label diaryNameLabel,
                                                              StringBuilder resultName) {
        workAreaMonthAndDiaryLabel.setVisible(true);
        workAreaMonthAndDiaryLabel.setText(resultName + ", " + diaryNameLabel.getText());
        workAreaMonthAndDiaryLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 19));
        workAreaMonthAndDiaryLabel.setTextFill(Color.valueOf("3D5064"));
    }

    public static void setMonthsForYearButton(Button button, String path) throws IOException {
        File monthsDir = new File(path + button.getText());
        List<String> lines = new ArrayList<>();
        addCategoriesStringToList(lines);
        for (Months nameOfMonth : Months.values()) {
            File monthFile = new File(monthsDir + "//" + nameOfMonth.toString() + ".txt");
            boolean isMonthFileCreated = monthFile.createNewFile();
            if (isMonthFileCreated) {
                @Cleanup FileWriter writer = new FileWriter(monthFile);
                for (String line : lines) {
                    writer.write(line + "\n");
                }
            }
        }
    }

    private static void addCategoriesStringToList(List<String> list) {
        for (AbstractCategory category : CategoriesUtils.getCategoriesAsList()) {
            list.add(category.getFieldToTxtFile());
        }
    }
}
