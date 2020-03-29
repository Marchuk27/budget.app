package sample;

import categories.AbstractCategory;
import com.sun.istack.internal.Nullable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import lombok.Cleanup;
import lombok.SneakyThrows;
import utils.EventHistoryUtils;

import static utils.CategoriesValueCalculator.*;
import static utils.CategoryValuesChangesMap.*;
import static utils.NodesUtils.*;
import static utils.PieChartSlicesMaker.*;
import static utils.CategoriesUtils.*;

import java.io.*;
import java.util.*;


public class ApplicationController extends StartConfigurator {
    @FXML private Rectangle head;
    @FXML private ImageView logoView;

    @FXML private AnchorPane eveningPane;
    @FXML private AnchorPane nightPane;
    @FXML private AnchorPane morningPane;
    @FXML private AnchorPane afternoonPane;
    List<AnchorPane> greetingPanesList;

    @FXML private Rectangle g1;
    @FXML private Rectangle g2;
    @FXML private Rectangle g3;
    @FXML private Rectangle g4;

    @FXML private Button diaryButton;
    @FXML private Button settingsButton;
    @FXML private Button themeButton;
    @FXML private Button infoButton;

    @FXML private AnchorPane pane;
    @FXML private AnchorPane menu;
    @FXML private FlowPane treeViewAreaPane = new FlowPane();
    @FXML private FlowPane paneWithMonthButtons;

    @FXML private Label deleteDiary;
    @FXML private Label addDiary;
    @FXML private Button acceptButton;

    @FXML private CheckBox deleteCheckBox1;
    @FXML private CheckBox deleteCheckBox2;
    @FXML private CheckBox deleteCheckBox3;
    @FXML private CheckBox deleteCheckBox4;
    List<CheckBox> checkBoxList;

    @FXML private Label diaryNameLabel;
    @FXML private Label workAreaMonthAndDiaryLabel;
    @FXML private Label backToMonthLabel;

    @FXML private AnchorPane paneWithCategories;

    @FXML private AnchorPane eventHistoryPane;
    @FXML private GridPane eventTable;
    @FXML private Label showEventHistory;
    @FXML private Label hideEventHistory;

    /**
     * Кнопки для месяцев
     */
    @FXML private Button januaryButton;
    @FXML private Button februaryButton;
    @FXML private Button marchButton;
    @FXML private Button aprilButton;
    @FXML private Button mayButton;
    @FXML private Button juneButton;
    @FXML private Button julyButton;
    @FXML private Button augustButton;
    @FXML private Button septemberButton;
    @FXML private Button octoberButton;
    @FXML private Button novemberButton;
    @FXML private Button decemberButton;

    /**
     * Лэйблы под категории
     */
    @FXML private Label costsLabel;
    @FXML private Label incomesLabel;

    @FXML private Label supermarketsLabel;
    @FXML private Label supermarketsValue = new Label("0");
    @FXML private Label resetSupermarketsValue = new Label();

    @FXML private Label beautyAndHealthLabel;
    @FXML private Label beautyAndHealthValue = new Label("0");
    @FXML private Label resetBeautyValue = new Label();

    @FXML private Label houseAndRepairsLabel;
    @FXML private Label houseAndRepairsValue = new Label("0");
    @FXML private Label resetHouseValue = new Label();

    @FXML private Label transportLabel;
    @FXML private Label transportValue = new Label("0");
    @FXML private Label resetTransportValue = new Label();

    @FXML private Label clothesAndShoesLabel;
    @FXML private Label clothesAndShoesValue = new Label("0");
    @FXML private Label resetClothesValue = new Label();

    @FXML private Label entertainmentsLabel;
    @FXML private Label entertainmentsValue = new Label("0");
    @FXML private Label resetEntertainmentsValue = new Label();

    @FXML private Label giftsLabel;
    @FXML private Label giftsValue = new Label("0");
    @FXML private Label resetGiftsValue = new Label();

    @FXML private Label stateServicesLabel;
    @FXML private Label stateServicesValue = new Label("0");
    @FXML private Label resetStateServicesValue = new Label();

    @FXML private Label zhkuLabel;
    @FXML private Label zhkuValue = new Label("0");
    @FXML private Label resetZhkuValue = new Label();

    @FXML private Label anotherCostsLabel;
    @FXML private Label anotherCostsValue = new Label("0");
    @FXML private Label resetAnotherCostsValue = new Label();

    @FXML private Label salaryLabel;
    @FXML private Label salaryValue = new Label("0");
    @FXML private Label resetSalaryValue = new Label();

    @FXML private Label businessLabel;
    @FXML private Label businessValue = new Label("0");
    @FXML private Label resetBusinessValue = new Label();

    @FXML private Label investLabel;
    @FXML private Label investValue = new Label("0");
    @FXML private Label resetInvestValue = new Label();

    @FXML private Label depositLabel;
    @FXML private Label depositValue = new Label("0");
    @FXML private Label resetDepositValue = new Label();

    @FXML private Label socialPaymentsLabel;
    @FXML private Label socialPaymentsValue = new Label("0");
    @FXML private Label resetSocialPaymentsValue = new Label();

    @FXML private Label anotherIncomesLabel;
    @FXML private Label anotherIncomesValue = new Label("0");
    @FXML private Label resetAnotherIncomesValue = new Label();

    @FXML private Label monthCosts = new Label();
    @FXML private Label monthIncomes = new Label();
    @FXML private Label revertLastValue = new Label();

    @FXML private PieChart incomesPieChart = new PieChart();
    @FXML private PieChart costsPieChart = new PieChart();

    @FXML private Button showSideButton;
    @FXML private Button hideSideButton;
    @FXML private AnchorPane sideBar;
    @FXML private Label differenceLabel;

    private ToggleGroup themeGroup = new ToggleGroup();
    @FXML private Label defaultThemeOn;
    @FXML private Label blueThemeOn;
    @FXML private Label redThemeOn;

    private List<AbstractCategory> categoriesList = getCategoriesAsList();
    private List<Label> categoriesLabelList = new ArrayList<>();
    private List<Label> categoriesValueList = new ArrayList<>();
    private List<Label> resetValuesLabelList = new ArrayList<>();
    private String[] pieColors = new String[16];
    List<PieChart.Data> slices = new ArrayList<>();
    List<PieChart.Data> slices2 = new ArrayList<>();

    public StringBuilder path;
    public List<String> readLines;

    String MAIN_PATH = StartConfigurator.determineStartPath();
    File folder = new File(new File(MAIN_PATH).getAbsolutePath());
    @Nullable
    File[] yearFilesArray = folder.listFiles();

    @FXML
    public void initialize() {
        greetingPanesList = createGreetingPanesList(morningPane, afternoonPane, eveningPane, nightPane);
        checkBoxList = createCheckBoxListForDelete(deleteCheckBox1, deleteCheckBox2,
                deleteCheckBox3, deleteCheckBox4);
        getStyleSheetsForNodes(pane, diaryButton, settingsButton, themeButton, infoButton, paneWithCategories);
        getStyleClassesForThemeButtons(defaultThemeOn, blueThemeOn, redThemeOn);
        calculateTime(greetingPanesList);
        setThemeButtonsLogic();
        diaryNameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 27));
        diaryNameLabel.setTextFill(Color.valueOf("3D5064"));
        addLabelsAndStylesToCategoriesLabelList();
        addLabelsToCategoriesValueList();
        monthCosts.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        monthIncomes.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        addLabelsToResetValuesLabelList();
        fillingColorsArray(pieColors);
        addSlicesToLists(slices, slices2, costsPieChart, incomesPieChart);
        setPieChartsData(slices, slices2, categoriesValueList, pieColors);
        setEventHandlerForLogoView(logoView);
        addStyleClassForEventHistoryLabels(showEventHistory, hideEventHistory);
        addStyleClassForSideMenuButtons(showSideButton, hideSideButton);

        //отображение дневников в пэйне по количеству лет
        treeViewAreaPane.setOrientation(Orientation.VERTICAL);
        if (yearFilesArray != null) {
            for (File filename : yearFilesArray) {
                Button btn = new Button(filename.getName());
                btn.getStyleClass().add("diaryListButton");
                treeViewAreaPane.getChildren().add(btn);
                addEventHandlerForFlowPaneChildrenButtons(btn);
                treeViewAreaPane.setVgap(4);
            }
        }

        /**
         кнопки
         */

        diaryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                treeViewAreaPane.setVisible(true);
                setFirstRectangleTrueVisible(g1, g2, g3, g4);
                settingsButton.setLayoutY(329);
                themeButton.setLayoutY(409);
                infoButton.setLayoutY(491);
                deleteDiary.setVisible(true);
                addDiary.setVisible(true);
                defaultThemeOn.setVisible(false);
                blueThemeOn.setVisible(false);
                redThemeOn.setVisible(false);
                setDayPartPanesVisibleFalse(greetingPanesList);
                setDefaultVisibleForElements(paneWithMonthButtons, paneWithCategories,
                        diaryNameLabel, workAreaMonthAndDiaryLabel, backToMonthLabel);
            }
        });
        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setFirstRectangleTrueVisible(g2, g1, g3, g4);
                defaultThemeOn.setVisible(false);
                blueThemeOn.setVisible(false);
                redThemeOn.setVisible(false);
                doActionsWithNodes();
                diaryButton.setDisable(false);
            }
        });
        themeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setFirstRectangleTrueVisible(g3, g2, g1, g4);
                defaultThemeOn.setVisible(true);
                blueThemeOn.setVisible(true);
                redThemeOn.setVisible(true);
                setDayPartPanesVisibleFalse(greetingPanesList);
                checkBoxList.forEach(cb -> cb.setSelected(false));
                setDefaultVisibleForCheckBox(acceptButton, checkBoxList);
                setConditionForThemeButton(treeViewAreaPane, settingsButton, themeButton,
                        infoButton, deleteDiary, addDiary);
                setDefaultVisibleForElements(paneWithMonthButtons, paneWithCategories, diaryNameLabel,
                        workAreaMonthAndDiaryLabel, backToMonthLabel);
                diaryButton.setDisable(false);
            }
        });
        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setFirstRectangleTrueVisible(g4, g2, g3, g1);
                defaultThemeOn.setVisible(false);
                blueThemeOn.setVisible(false);
                redThemeOn.setVisible(false);
                doActionsWithNodes();
                diaryButton.setDisable(false);
            }
        });
        deleteDiary.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                File yearFilesDir = new File(MAIN_PATH);
                File[] yearFilesArr = yearFilesDir.listFiles();
                if (yearFilesArr != null) {
                    int buttonAmount = yearFilesArr.length;
                    switch (buttonAmount) {
                        case 1:
                            deleteCheckBox1.setVisible(true);
                            break;
                        case 2:
                            deleteCheckBox1.setVisible(true);
                            deleteCheckBox2.setVisible(true);
                            break;
                        case 3:
                            deleteCheckBox1.setVisible(true);
                            deleteCheckBox2.setVisible(true);
                            deleteCheckBox3.setVisible(true);
                            break;
                        case 4:
                            checkBoxList.forEach(cb -> cb.setVisible(true));
                            break;
                    }
                    addDiary.setDisable(true);
                    acceptButton.setVisible(true);
                    deleteDiary.setVisible(false);
                }
            }
        });
        addDiary.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                File[] yearFilesArr = folder.listFiles();
                Image image = new Image("/resources/png_pics/addDiaryAlertLogo.png");
                ImageView imageView = new ImageView(image);
                if (yearFilesArr != null) {
                    int buttonAmount = yearFilesArr.length;
                    if (buttonAmount >= 4) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setGraphic(imageView);
                        alert.setHeaderText(null);
                        alert.setTitle("Ошибка добавления");
                        String s = "Максимальное количество дневников - 4. " + "Чтобы добавить новый дневник, удалите один из существующих";
                        alert.setContentText(s);
                        alert.showAndWait();
                    } else {
                        TextInputDialog textInputDialog = new TextInputDialog();
                        textInputDialog.setHeaderText(null);
                        textInputDialog.setGraphic(imageView);
                        textInputDialog.setTitle("Введите название дневника:");
                        try {
                            addDiaryNameLogic(textInputDialog);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (yearFilesArray == null) {
                    {
                        TextInputDialog textInputDialog = new TextInputDialog();
                        textInputDialog.setHeaderText(null);
                        textInputDialog.setGraphic(imageView);
                        textInputDialog.setTitle("Введите название дневника:");
                        try {
                            addDiaryNameLogic(textInputDialog);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        acceptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                File[] yearsArray = folder.listFiles();
                assert yearsArray != null;
                Arrays.sort(yearsArray, Comparator.comparingLong(File::lastModified));
                setDefaultVisibleForCheckBox(acceptButton, checkBoxList);
                for (int i = 4; i > 0; i--) {
                    if (checkBoxList.get(i - 1).isSelected()) {
                        treeViewAreaPane.getChildren().remove(i - 1);
                        File deletedFile = new File(yearsArray[i - 1].toString());
                        String[] directoryFiles = deletedFile.list();
                        assert directoryFiles != null;
                        for (final String filename : directoryFiles) { //сначала чистим директорию от txt файлов-месяцев
                            File tempFile = new File(deletedFile.getAbsolutePath() + File.separator + filename);
                            tempFile.delete();
                        }
                        //затем удаляем сам файл
                        boolean yearFile = deletedFile.delete();
                    }
                }

                setDefaultConditionForCheckBox(checkBoxList);
                addDiary.setDisable(false);
                deleteDiary.setVisible(true);
                paneWithMonthButtons.setVisible(false);
                diaryNameLabel.setVisible(false);
            }
        });

        backToMonthLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                paneWithMonthButtons.setVisible(true);
                diaryNameLabel.setVisible(true);
                paneWithCategories.setVisible(false);
                workAreaMonthAndDiaryLabel.setVisible(false);
                backToMonthLabel.setVisible(false);
                int slashIndex = path.lastIndexOf("/");
                path.delete(slashIndex + 1, path.length());

                treeViewAreaPane.setVisible(true);
                settingsButton.setLayoutY(329);
                themeButton.setLayoutY(409);
                infoButton.setLayoutY(491);
                deleteDiary.setVisible(true);
                addDiary.setVisible(true);
            }
        });

        showEventHistory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @SneakyThrows
            @Override
            public void handle(MouseEvent event)  {
                showEventHistory.setVisible(false);
                hideEventHistory.setVisible(true);
                eventHistoryPane.setVisible(true);
                String diaryName = diaryNameLabel.getText();
                String monthName = workAreaMonthAndDiaryLabel.getText().substring(0, workAreaMonthAndDiaryLabel.getText().indexOf(','));
                EventHistoryUtils.createEventsHistoryTable(eventTable, "/" + diaryName + "/" + monthName);
            }
        });

        hideEventHistory.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showEventHistory.setVisible(true);
                hideEventHistory.setVisible(false);
                eventHistoryPane.setVisible(false);
                sideBar.setVisible(false);
                hideSideButton.setVisible(false);
                eventTable.getChildren().removeIf(node -> GridPane.getRowIndex(node) != 0);
            }
        });

        showSideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sideBar.setVisible(true);
                differenceLabel.setText(calculateProfit(monthIncomes, monthCosts));
                hideSideButton.setVisible(true);
                showSideButton.setVisible(false);
                eventHistoryPane.setVisible(false);
                hideEventHistory.setVisible(false);
            }
        });

        hideSideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sideBar.setVisible(false);
                hideSideButton.setVisible(false);
                showSideButton.setVisible(true);
            }
        });

        revertLastValue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!getValuesChangeMap().isEmpty()) {
                    try {
                        revertValue(categoriesValueList, path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                setMonthCostsAndIncomes();
            }
        });
    }

    /**
     кнопки
     */

    private void addDiaryNameLogic(TextInputDialog dialog) throws IOException {
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            char[] enteredDiaryName = result.get().toCharArray();
            int count = 0;
            for (char c : enteredDiaryName) {
                if ((int) c < 48 || ((int) c > 57 && (int) c < 65) || ((int) c > 90 && (int) c < 97) || ((int) c > 122 && (int) c < 127)) {
                    break;
                }
                count++;
            }
            if (count == enteredDiaryName.length) {
                File diaryFolder = new File(MAIN_PATH + dialog.getResult());
                if (!diaryFolder.exists()) {
                    boolean isDirectoryCreated = diaryFolder.mkdir();
                    Button btn = new Button(dialog.getResult());
                    btn.getStyleClass().add("diaryListButton");
                    treeViewAreaPane.getChildren().add(btn);
                    if (isDirectoryCreated) {
                        setMonthsForYearButton(btn, MAIN_PATH);
                        addEventHandlerForFlowPaneChildrenButtons(btn);
                    }
                } else if (dialog.getResult().isEmpty()) {
                    dialog.setHeaderText("Это поле не может быть пустым");
                    dialog.setWidth(330);
                    dialog.setHeight(196);
                    addDiaryNameLogic(dialog);
                } else {
                    dialog.setHeaderText("Дневник с таким названием уже существует");
                    dialog.setWidth(330);
                    dialog.setHeight(196);
                    addDiaryNameLogic(dialog);
                }
            } else {
                dialog.setHeaderText("Название может содержать только буквы и цифры");
                dialog.setWidth(330);
                dialog.setHeight(198);
                addDiaryNameLogic(dialog);
            }
        }
    }

    private void addEventHandlerForFlowPaneChildrenButtons(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                paneWithMonthButtons.setVisible(true);
                addStylesAndActionsForMonthButtons();
                StringBuilder buttonText = new StringBuilder(button.getText());
                Text diaryNameText = new Text(buttonText.toString());
                diaryNameLabel.setText(diaryNameText.getText());
                path = new StringBuilder(MAIN_PATH);
                path.append(buttonText).append("/");
                diaryButton.setDisable(true);
                diaryNameLabel.setVisible(true);
            }
        });
    }

    private void addStylesAndActionsForMonthButtons() {
        paneWithMonthButtons.getStylesheets().add(getClass().getResource("/css/ButtonStyles.css").toExternalForm());
        List<Button> buttonList = new ArrayList<>();
        fillMonthButtonList(buttonList);
        for (Button btn : buttonList) {
            btn.getStyleClass().add("monthButton");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setDefaultConditionForButtons(treeViewAreaPane, settingsButton, themeButton, infoButton,
                            deleteDiary, addDiary);
                    paneWithMonthButtons.setVisible(false);
                    diaryNameLabel.setVisible(false);
                    paneWithCategories.setVisible(true);
                    backToMonthLabel.setVisible(true);
                    int len = btn.getId().length();
                    StringBuilder buttonName = new StringBuilder(btn.getId().substring(0, len - 6));
                    StringBuilder resultName = new StringBuilder(buttonName.toString().substring(0, 1).toUpperCase() + buttonName.toString().substring(1));

                    setParamsForWorkAreaMonthAndDiaryLabel(workAreaMonthAndDiaryLabel, diaryNameLabel, resultName);
                    path.append(buttonName).append(".txt");
                    try {
                        readLinesFromTxt(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setMonthCostsAndIncomes();
                }
            });
        }
    }

    private void readLinesFromTxt(StringBuilder path) throws IOException {
        @Cleanup FileReader fileReader = new FileReader(path.toString());
        @Cleanup BufferedReader bufferedReader = new BufferedReader(fileReader);
        readLines = new ArrayList<>();
        String fileStr;
        while ((fileStr = bufferedReader.readLine()) != null) {
            readLines.add(fileStr);
        }
        int k = 0;
        for (Label categoryValueLabel : categoriesValueList) {
            int indexOfDoubleDot = readLines.get(k).indexOf(":");
            int lengthOfLine = readLines.get(k).length();
            StringBuilder categoryValueTrimmedFromLine = new StringBuilder(readLines.get(k).substring(indexOfDoubleDot + 1, lengthOfLine).trim());
            categoryValueLabel.setText(categoryValueTrimmedFromLine.toString());
            categoryValueLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
            categoryValueLabel.setVisible(true);
            k++;
        }
    }

    private void addLabelsToCategoriesValueList() {
        categoriesValueList.add(supermarketsValue);
        categoriesValueList.add(beautyAndHealthValue);
        categoriesValueList.add(houseAndRepairsValue);
        categoriesValueList.add(transportValue);
        categoriesValueList.add(clothesAndShoesValue);
        categoriesValueList.add(entertainmentsValue);
        categoriesValueList.add(giftsValue);
        categoriesValueList.add(stateServicesValue);
        categoriesValueList.add(zhkuValue);
        categoriesValueList.add(anotherCostsValue);
        categoriesValueList.add(salaryValue);
        categoriesValueList.add(businessValue);
        categoriesValueList.add(investValue);
        categoriesValueList.add(depositValue);
        categoriesValueList.add(socialPaymentsValue);
        categoriesValueList.add(anotherIncomesValue);
        for (Label categoryValueLabel : categoriesValueList) {
            categoryValueLabel.setText("0");
            categoryValueLabel.getStyleClass().add("labelValue");
        }
    }

    private void addLabelsAndStylesToCategoriesLabelList() {
        addLabelsForCategoryList();
        Tooltip tooltip = createToolTipsForCategories();
        for (Label myLabel : getCategoriesLabels(categoriesList)) {
            myLabel.getStyleClass().add("myLabel");
            myLabel.setTooltip(tooltip);
            myLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 1) {
                            TextInputDialog textInputDialog = new TextInputDialog();
                            textInputDialog.setHeaderText(null);
                            Image image = new Image("/resources/png_pics/sumValue.png");
                            ImageView imageView = new ImageView(image);
                            textInputDialog.setGraphic(imageView);
                            textInputDialog.setTitle("Введите сумму:");
                            try {
                               enterTheValueAndSummarizeWithCurrent(textInputDialog, categoriesLabelList.indexOf(myLabel),
                                       path, categoriesValueList);
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            textInputDialog.close();
                            setMonthCostsAndIncomes();
                        }
                    }
                }
            });
        }
        Tooltip cancelTooltip = new Tooltip("Отменить последнее действие");
        cancelTooltip.setPrefSize(159, 14);
        cancelTooltip.setStyle("-fx-background-color: #E9E1E1; -fx-text-fill: black;");
        cancelTooltip.setOpacity(0.9);
        revertLastValue.setTooltip(cancelTooltip);
        revertLastValue.getStyleClass().add("revertValueLabel");
    }

    private void addLabelsForCategoryList() {
        fillCategoriesLabelList(categoriesLabelList);
        int i = 0;
        for (AbstractCategory category : categoriesList) {
            category.setCategoryLabel(categoriesLabelList.get(i));
            i++;
        }
    }

    private void fillCategoriesLabelList(List<Label> categoriesLabelList) {
        categoriesLabelList.add(supermarketsLabel);
        categoriesLabelList.add(beautyAndHealthLabel);
        categoriesLabelList.add(houseAndRepairsLabel);
        categoriesLabelList.add(transportLabel);
        categoriesLabelList.add(clothesAndShoesLabel);
        categoriesLabelList.add(entertainmentsLabel);
        categoriesLabelList.add(giftsLabel);
        categoriesLabelList.add(stateServicesLabel);
        categoriesLabelList.add(zhkuLabel);
        categoriesLabelList.add(anotherCostsLabel);
        categoriesLabelList.add(salaryLabel);
        categoriesLabelList.add(businessLabel);
        categoriesLabelList.add(investLabel);
        categoriesLabelList.add(depositLabel);
        categoriesLabelList.add(socialPaymentsLabel);
        categoriesLabelList.add(anotherIncomesLabel);
    }

    private void addLabelsToResetValuesLabelList() {
        resetValuesLabelList.add(resetSupermarketsValue);
        resetValuesLabelList.add(resetBeautyValue);
        resetValuesLabelList.add(resetHouseValue);
        resetValuesLabelList.add(resetTransportValue);
        resetValuesLabelList.add(resetClothesValue);
        resetValuesLabelList.add(resetEntertainmentsValue);
        resetValuesLabelList.add(resetGiftsValue);
        resetValuesLabelList.add(resetStateServicesValue);
        resetValuesLabelList.add(resetZhkuValue);
        resetValuesLabelList.add(resetAnotherCostsValue);
        resetValuesLabelList.add(resetSalaryValue);
        resetValuesLabelList.add(resetBusinessValue);
        resetValuesLabelList.add(resetInvestValue);
        resetValuesLabelList.add(resetDepositValue);
        resetValuesLabelList.add(resetSocialPaymentsValue);
        resetValuesLabelList.add(resetAnotherIncomesValue);
        Tooltip tooltip = new Tooltip("Обнулить значение");
        tooltip.setStyle("-fx-background-color: #E9E1E1; -fx-text-fill: black;");
        tooltip.setOpacity(0.83);
        for (Label lbl : resetValuesLabelList) {
            lbl.getStyleClass().add("myLabel");
            lbl.setTooltip(tooltip);
            lbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        resetLabelValue(resetValuesLabelList.indexOf(lbl), path, categoriesValueList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setMonthCostsAndIncomes();
                }
            });
        }
    }

    private void doActionsWithNodes() {
        setDayPartPanesVisibleFalse(greetingPanesList);
        checkBoxList.forEach(cb -> cb.setSelected(false));
        setDefaultVisibleForCheckBox(acceptButton, checkBoxList);

        setDefaultConditionForButtons(treeViewAreaPane, settingsButton, themeButton, infoButton, deleteDiary, addDiary);
        setDefaultVisibleForElements(paneWithMonthButtons, paneWithCategories, diaryNameLabel, workAreaMonthAndDiaryLabel,
                backToMonthLabel);
    }

    private void setMonthCostsAndIncomes() {
        monthCosts.setText((calculateGeneralCosts(categoriesValueList, slices, slices2, pieColors)));
        monthIncomes.setText((calculateGeneralIncomes(categoriesValueList, slices, slices2, pieColors)));
    }

    private void setThemeButtonsLogic() {
        defaultThemeOn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeColorForThemeElements(Color.rgb(122, 191, 52));
                pane.setStyle("-fx-background-color: #CCDCE3;");
                menu.setStyle("-fx-background-color: #3D5064;");
                removeStyleClassesForButtons();
                addStyleClassesForButtons();
                setEventHandlerForLogoView(logoView);
                deleteDiary.setStyle("-fx-background-image: url('/resources/png_pics/deleteButton.png');");
                addDiary.setStyle("-fx-background-image: url('/resources/png_pics/addButtonPic.png');");
                treeViewAreaPane.setStyle("-fx-background-color: #3D5064;");
                diaryNameLabel.setTextFill(Color.web("3D5064"));
                backToMonthLabel.setStyle("-fx-background-image: url('/resources/months/backToMonth.png');" +
                        "-fx-cursor: hand; -fx-background-repeat: no-repeat");
                workAreaMonthAndDiaryLabel.setTextFill(Color.rgb(61, 80, 100));
            }
        });
        blueThemeOn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeColorForThemeElements(Color.rgb(39, 168, 160));
                pane.setStyle("-fx-background-color: #5E8A9D;");
                menu.setStyle("-fx-background-color: #3D5064;");
                removeStyleClassesForButtons();
                addStyleClassesForButtons();
                setEventHandlerForBlueLogoView(logoView);
                deleteDiary.setStyle("-fx-background-image: url('/resources/png_pics/deleteButton.png');");
                addDiary.setStyle("-fx-background-image: url('/resources/png_pics/addButtonPic.png');");
                treeViewAreaPane.setStyle("-fx-background-color: #3D5064;");
                diaryNameLabel.setTextFill(Color.web("3D5064"));
                backToMonthLabel.setStyle("-fx-background-image: url('/resources/months/backToMonth.png');" +
                        "-fx-cursor: hand; -fx-background-repeat: no-repeat");
                workAreaMonthAndDiaryLabel.setTextFill(Color.rgb(61, 80, 100));
            }
        });

        redThemeOn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeColorForThemeElements(Color.rgb(143, 14, 38));
                pane.setStyle("-fx-background-color: #9F999B;");
                menu.setStyle("-fx-background-color: #625F74;");
                removeStyleClassesForButtons();
                diaryButton.getStyleClass().add("redThemeDiaryButton");
                settingsButton.getStyleClass().add("redThemeSettingsButton");
                themeButton.getStyleClass().add("redThemeThemeButton");
                infoButton.getStyleClass().add("redThemeInfoButton");
                setEventHandlerForRedLogoView(logoView);
                deleteDiary.setStyle("-fx-background-image: url('/resources/png_pics/red_deleteButton.png');");
                addDiary.setStyle("-fx-background-image: url('/resources/png_pics/red_addButtonPic.png');" +
                                "-fx-background-repeat: no-repeat");
                treeViewAreaPane.setStyle("-fx-background-color: #625F74;");
                diaryNameLabel.setTextFill(Color.rgb(98, 95, 116));
                backToMonthLabel.setStyle("-fx-background-image: url('/resources/months/red_backToMonth.png');" +
                        "-fx-cursor: hand; -fx-background-repeat: no-repeat");
                workAreaMonthAndDiaryLabel.setTextFill(Color.rgb(98, 95, 116));
            }
        });
    }

    private void changeColorForThemeElements(Color colorValue) {
        g1.setFill(colorValue);
        g2.setFill(colorValue);
        g3.setFill(colorValue);
        g4.setFill(colorValue);
        head.setFill(colorValue);
    }

    private void removeStyleClassesForButtons() {
        diaryButton.getStyleClass().remove(1, 2);
        settingsButton.getStyleClass().remove(1, 2);
        themeButton.getStyleClass().remove(1, 2);
        infoButton.getStyleClass().remove(1,2);
    }

    private void addStyleClassesForButtons() {
        diaryButton.getStyleClass().add("diaryButton");
        settingsButton.getStyleClass().add("settingsButton");
        themeButton.getStyleClass().add("themeButton");
        infoButton.getStyleClass().add("infoButton");
    }

    private void fillMonthButtonList(List<Button> buttonList) {
        buttonList.add(januaryButton);
        buttonList.add(februaryButton);
        buttonList.add(marchButton);
        buttonList.add(aprilButton);
        buttonList.add(mayButton);
        buttonList.add(juneButton);
        buttonList.add(julyButton);
        buttonList.add(augustButton);
        buttonList.add(septemberButton);
        buttonList.add(octoberButton);
        buttonList.add(novemberButton);
        buttonList.add(decemberButton);
    }

    private Tooltip createToolTipsForCategories() {
        Tooltip tooltip = new Tooltip("Нажмите, чтобы добавить сумму");
        tooltip.setPrefSize(180, 14);
        tooltip.setStyle("-fx-background-color: #E9E1E1; -fx-text-fill: black;");
        tooltip.setOpacity(0.9);

        Tooltip showHistoryToolTip = new Tooltip("Показать последние операции");
        showHistoryToolTip.setPrefSize(165, 14);
        showHistoryToolTip.setStyle("-fx-background-color: #E9E1E1; -fx-text-fill: black;");
        showHistoryToolTip.setOpacity(0.9);
        showEventHistory.setTooltip(showHistoryToolTip);
        return tooltip;
    }
}
