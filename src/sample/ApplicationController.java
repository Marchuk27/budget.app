package sample;

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
import lombok.SneakyThrows;
import utils.EventHistoryUtils;

import static utils.CategoriesValueCalculator.*;
import static utils.CategoryValuesChangesMap.*;
import static utils.NodesActionClass.*;
import static utils.PieChartSlicesMaker.*;

import java.io.*;
import java.util.*;


public class ApplicationController extends StartConfigurator {
    @FXML private Rectangle head;
    @FXML private ImageView logoView;

    @FXML private AnchorPane goodEveningPane;
    @FXML private AnchorPane goodNightPane;
    @FXML private AnchorPane goodMorningPane;
    @FXML private AnchorPane goodAfternoonPane;

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

    @FXML private CheckBox deleteDiaryCheckBox1;
    @FXML private CheckBox deleteDiaryCheckBox2;
    @FXML private CheckBox deleteDiaryCheckBox3;
    @FXML private CheckBox deleteDiaryCheckBox4;

    @FXML private Label diaryNameLabel;
    @FXML private Label workAreaMonthAndDiaryLabel;
    @FXML private Label backToMonthLabel;
    @FXML private Label labelWithMonth;

    @FXML private AnchorPane paneWithCategories;

    @FXML private AnchorPane eventHistoryPane;
    @FXML private ScrollPane eventScrollPane;
    @FXML private GridPane eventTable;
    @FXML private Button showEventHistory;
    @FXML private Button hideEventHistory;

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
    @FXML private ToggleButton defaultThemeOn = new ToggleButton();
    @FXML private ToggleButton blueThemeOn = new ToggleButton();
    @FXML private ToggleButton redThemeOn = new ToggleButton();

    private List<Label> categoriesLabelList = new ArrayList<Label>();
    private List<Label> categoriesValueList = new ArrayList<Label>();
    private List<Label> resetValuesLabelList = new ArrayList<Label>();
    private String[] pieColors = new String[16];
    List<PieChart.Data> slices = new ArrayList<>();
    List<PieChart.Data> slices2 = new ArrayList<>();

    public StringBuilder path;
    public List<String> readLines;

    String MAIN_PATH = StartConfigurator.determineStartPath();
    File folder = new File(new File(MAIN_PATH).getAbsolutePath());
    File[] yearFilesArray = folder.listFiles();

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        getStyleSheetsForNodes(pane, diaryButton, settingsButton, themeButton, infoButton, paneWithCategories);
        getStyleClassesForThemeButtons(defaultThemeOn, blueThemeOn, redThemeOn);
        calculateTime(goodMorningPane, goodAfternoonPane, goodEveningPane, goodNightPane);
        setThemeButtonsLogic();
        diaryNameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 27));
        diaryNameLabel.setTextFill(Color.valueOf("3D5064"));
        addLabelsToCategoriesValueList();
        addLabelsAndStylesToCategoriesLabelList();
        monthCosts.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        monthIncomes.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        addLabelsToResetValuesLabelList();
        fillingColorsArray(pieColors);
        addSlicesToLists(slices, slices2, costsPieChart, incomesPieChart);
        setPieChartsData(slices, slices2, categoriesValueList, pieColors);
        setEventHandlerForLogoView(logoView);
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
                g1.setVisible(true);
                g2.setVisible(false);
                g3.setVisible(false);
                g4.setVisible(false);
                settingsButton.setLayoutY(329);
                themeButton.setLayoutY(409);
                infoButton.setLayoutY(491);
                deleteDiary.setVisible(true);
                addDiary.setVisible(true);
                defaultThemeOn.setVisible(false);
                blueThemeOn.setVisible(false);
                redThemeOn.setVisible(false);
                setDayPartPanesVisibleFalse(goodMorningPane, goodAfternoonPane, goodEveningPane,
                        goodNightPane);
                setDefaultVisibleForElements(paneWithMonthButtons, paneWithCategories,
                        diaryNameLabel, workAreaMonthAndDiaryLabel, backToMonthLabel, labelWithMonth);
            }
        });
        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                g1.setVisible(false);
                g2.setVisible(true);
                g3.setVisible(false);
                g4.setVisible(false);
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
                defaultThemeOn.setVisible(true);
                blueThemeOn.setVisible(true);
                redThemeOn.setVisible(true);
                g1.setVisible(false);
                g2.setVisible(false);
                g3.setVisible(true);
                g4.setVisible(false);
                setDayPartPanesVisibleFalse(goodMorningPane, goodAfternoonPane, goodEveningPane,
                        goodNightPane);
                setDefaultConditionForCheckBox(deleteDiaryCheckBox1, deleteDiaryCheckBox2, deleteDiaryCheckBox3, deleteDiaryCheckBox4);
                setDefaultVisibleForCheckBox(acceptButton, deleteDiaryCheckBox1, deleteDiaryCheckBox2, deleteDiaryCheckBox3, deleteDiaryCheckBox4);
                setConditionForThemeButton(treeViewAreaPane, settingsButton, themeButton,
                        infoButton, deleteDiary, addDiary);
                setDefaultVisibleForElements(paneWithMonthButtons, paneWithCategories, diaryNameLabel,
                        workAreaMonthAndDiaryLabel, backToMonthLabel, labelWithMonth);
                diaryButton.setDisable(false);
            }
        });
        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                g1.setVisible(false);
                g2.setVisible(false);
                g3.setVisible(false);
                g4.setVisible(true);
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
                            deleteDiaryCheckBox1.setVisible(true);
                            break;
                        case 2:
                            deleteDiaryCheckBox1.setVisible(true);
                            deleteDiaryCheckBox2.setVisible(true);
                            break;
                        case 3:
                            deleteDiaryCheckBox1.setVisible(true);
                            deleteDiaryCheckBox2.setVisible(true);
                            deleteDiaryCheckBox3.setVisible(true);
                            break;
                        case 4:
                            deleteDiaryCheckBox1.setVisible(true);
                            deleteDiaryCheckBox2.setVisible(true);
                            deleteDiaryCheckBox3.setVisible(true);
                            deleteDiaryCheckBox4.setVisible(true);
                            break;
                    }
                    addDiary.setDisable(true);
                    acceptButton.setVisible(true);
                    deleteDiary.setVisible(false);
                }
            }
        });
        addDiary.setOnMouseClicked(new EventHandler<MouseEvent>() {
            Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Emojione_1F62D.svg/64px-Emojione_1F62D.svg.png");
            ImageView imageView = new ImageView(image);

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
                setDefaultVisibleForCheckBox(acceptButton, deleteDiaryCheckBox1, deleteDiaryCheckBox2, deleteDiaryCheckBox3, deleteDiaryCheckBox4);
                List<CheckBox> checkBoxList = new ArrayList<>();
                checkBoxList.add(deleteDiaryCheckBox1);
                checkBoxList.add(deleteDiaryCheckBox2);
                checkBoxList.add(deleteDiaryCheckBox3);
                checkBoxList.add(deleteDiaryCheckBox4);
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

                setDefaultConditionForCheckBox(deleteDiaryCheckBox1, deleteDiaryCheckBox2, deleteDiaryCheckBox3, deleteDiaryCheckBox4);
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
                labelWithMonth.setVisible(false);
                int slashIndex = path.lastIndexOf("/");
                path.delete(slashIndex + 1, path.length());
            }
        });
        /**
         кнопки
         */
        showEventHistory.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event)  {
                showEventHistory.setVisible(false);
                hideEventHistory.setVisible(true);
                eventHistoryPane.setVisible(true);
                String diaryName = diaryNameLabel.getText();
                String monthName = workAreaMonthAndDiaryLabel.getText().substring(0, workAreaMonthAndDiaryLabel.getText().indexOf(','));
                EventHistoryUtils.createEventsHistoryTable(eventTable, "/" + diaryName + "/" + monthName);
            }
        });

        hideEventHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showEventHistory.setVisible(true);
                hideEventHistory.setVisible(false);
                eventHistoryPane.setVisible(false);
                eventTable.getChildren().removeIf(node -> GridPane.getRowIndex(node) != 0);
            }
        });

        showSideButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sideBar.setVisible(true);
                differenceLabel.setText(calculateProfit());
                hideSideButton.setVisible(true);
                showSideButton.setVisible(false);
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
                diaryNameText.setFont(Font.loadFont("resources/fonts/montserrat/Montserrat-Bold.ttf", 120));
                diaryNameLabel.setText(diaryNameText.getText());
                path = new StringBuilder(MAIN_PATH);
                path.append(buttonText).append("/");
                diaryButton.setDisable(true);
                diaryNameLabel.setVisible(true);
            }
        });
    }

    private void addStylesAndActionsForMonthButtons() {
        paneWithMonthButtons.getStylesheets().add(getClass().getResource("/css/HoverButton.css").toExternalForm());
        List<Button> buttonList = new ArrayList<>(Arrays.asList(januaryButton, februaryButton, marchButton, aprilButton));
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
        for (Button btn : buttonList) {
            btn.getStyleClass().add("monthButton");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setDefaultConditionForButtons(treeViewAreaPane, settingsButton,
                            themeButton, infoButton, deleteDiary, addDiary);
                    paneWithMonthButtons.setVisible(false);
                    diaryNameLabel.setVisible(false);
                    paneWithCategories.setVisible(true);
                    backToMonthLabel.setVisible(true);
                    labelWithMonth.setVisible(true);
                    workAreaMonthAndDiaryLabel.setVisible(true);
                    int len = btn.getId().length();
                    StringBuilder buttonName = new StringBuilder(btn.getId().substring(0, len - 6));
                    StringBuilder resultName = new StringBuilder(buttonName.toString().substring(0, 1).toUpperCase() + buttonName.toString().substring(1));
                    workAreaMonthAndDiaryLabel.setText(resultName + ", " + diaryNameLabel.getText());
                    workAreaMonthAndDiaryLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 19));
                    workAreaMonthAndDiaryLabel.setTextFill(Color.valueOf("3D5064"));
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
        FileReader fileReader = new FileReader(path.toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
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
        bufferedReader.close();
        fileReader.close();
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
        Tooltip tooltip = new Tooltip("Нажмите, чтобы добавить сумму");
        tooltip.setPrefSize(180, 14);
        tooltip.setStyle("-fx-background-color: #E9E1E1; -fx-text-fill: black;");
        tooltip.setOpacity(0.9);
        for (Label myLabel : categoriesLabelList) {
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
        setDayPartPanesVisibleFalse(goodMorningPane, goodAfternoonPane, goodEveningPane, goodNightPane);

        setDefaultConditionForCheckBox(deleteDiaryCheckBox1, deleteDiaryCheckBox2, deleteDiaryCheckBox3, deleteDiaryCheckBox4);
        setDefaultVisibleForCheckBox(acceptButton, deleteDiaryCheckBox1, deleteDiaryCheckBox2, deleteDiaryCheckBox3, deleteDiaryCheckBox4);

        setDefaultConditionForButtons(treeViewAreaPane, settingsButton, themeButton, infoButton, deleteDiary, addDiary);
        setDefaultVisibleForElements(paneWithMonthButtons, paneWithCategories, diaryNameLabel, workAreaMonthAndDiaryLabel,
                backToMonthLabel, labelWithMonth);
    }

    private void setMonthCostsAndIncomes() {
        monthCosts.setText(Integer.toString(calculateGeneralCosts(categoriesValueList, slices, slices2, pieColors)));
        monthIncomes.setText(Integer.toString(calculateGeneralIncomes(categoriesValueList, slices, slices2, pieColors)));
    }

    private String calculateProfit() {
        int profit = Integer.parseInt(monthIncomes.getText()) - Integer.parseInt(monthCosts.getText());
        if (profit > 0) {
            return "+" + String.valueOf(profit);
        }
        return String.valueOf(profit);
    }

    private void setThemeButtonsLogic() {
        defaultThemeOn.setToggleGroup(themeGroup);
        blueThemeOn.setToggleGroup(themeGroup);
        defaultThemeOn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                defaultThemeOn.setSelected(true);
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
                backToMonthLabel.setStyle("-fx-background-image: url('/resources/months/back_button.png');" +
                        "-fx-cursor: hand; -fx-background-repeat: no-repeat");
                labelWithMonth.setStyle("-fx-background-image: url('/resources/months/month_label.png');" +
                        "-fx-background-repeat: no-repeat");
                workAreaMonthAndDiaryLabel.setTextFill(Color.rgb(61, 80, 100));
            }
        });
        blueThemeOn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                blueThemeOn.setSelected(true);
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
                backToMonthLabel.setStyle("-fx-background-image: url('/resources/months/back_button.png');" +
                        "-fx-cursor: hand; -fx-background-repeat: no-repeat");
                labelWithMonth.setStyle("-fx-background-image: url('/resources/months/month_label.png');" +
                        "-fx-background-repeat: no-repeat");
                workAreaMonthAndDiaryLabel.setTextFill(Color.rgb(61, 80, 100));
            }
        });

        redThemeOn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                redThemeOn.setSelected(true);
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
                addDiary.setStyle("-fx-background-image: url('/resources/png_pics/red_addButtonPic.png');");
                treeViewAreaPane.setStyle("-fx-background-color: #625F74;");
                diaryNameLabel.setTextFill(Color.rgb(98, 95, 116));
                backToMonthLabel.setStyle("-fx-background-image: url('/resources/months/redtheme_back_button.png');" +
                        "-fx-cursor: hand; -fx-background-repeat: no-repeat");
                labelWithMonth.setStyle("-fx-background-image: url('/resources/months/red_theme_month_label.png');" +
                        "-fx-background-repeat: no-repeat");
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
}
