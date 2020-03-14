package utils;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static utils.CategoryValuesChangesMap.*;
import static utils.DateUtils.*;

public class CategoriesValueCalculator {

    public static void resetLabelValue(int labelNum, StringBuilder path, List<Label> categoriesValueList) throws IOException {
        FileReader fileReader = new FileReader(path.toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> readLines = new ArrayList<>();
        String fileStr;
        while ((fileStr = bufferedReader.readLine()) != null) {
            if (readLines.size() < 16) {
                readLines.add(fileStr);
            }
        }
        int indexOfDoubleDot = readLines.get(labelNum).indexOf(":");
        int lengthOfLine = readLines.get(labelNum).length();
        StringBuilder currentNum = new StringBuilder(readLines.get(labelNum).substring(indexOfDoubleDot + 1, lengthOfLine).trim());
        putValueIntoHistoryChangesMap(labelNum, Long.parseLong(currentNum.toString()));
        long resultNum = 0L;
        categoriesValueList.get(labelNum).setText(Long.toString(resultNum));
        StringBuilder zeroValue = new StringBuilder(readLines.get(labelNum).replace(currentNum.toString(), "0"));
        if (new File(path.toString()).exists()) {
            FileWriter fileWriter = new FileWriter(path.toString());
            readLines.set(labelNum, zeroValue.toString());
            for (String val : readLines) {
                fileWriter.write(val + "\n");
            }
            fileWriter.close();
        }
        fileReader.close();
        bufferedReader.close();
    }

    public static void enterTheValueAndSummarizeWithCurrent(TextInputDialog dialog, int labelNum, StringBuilder path,
                                                       List<Label> categoriesValueList) throws IOException, ClassNotFoundException {
        Optional<String> result = dialog.showAndWait();
        FileReader fileReader = new FileReader(path.toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> readLines = new ArrayList<String>();
        String monthName = path.substring(path.lastIndexOf("/") , path.length() - 4);
        String pathToDiary = path.substring(0, path.indexOf(monthName));
        String monthNameWithDiary = pathToDiary.substring(pathToDiary.lastIndexOf("/") + 1) + monthName;

        if (result.isPresent() && (!NumberUtils.isCreatable(result.get()) || Long.parseLong(result.get()) < 0)) {
            enterTheValueAndSummarizeWithCurrent(dialog, labelNum, path, categoriesValueList);
        } else if (result.isPresent()) {
            EventHistoryUtils.saveEventsHistoryToTxt(monthNameWithDiary, getCurrentDateInCorrectFormat(),
                    getCurrentTime(), labelNum, Integer.parseInt(result.get()));
            String fileStr1;
            while ((fileStr1 = bufferedReader.readLine()) != null) {
                readLines.add(fileStr1);
            }
            int indexOfDoubleDot = readLines.get(labelNum).indexOf(":");
            int length = readLines.get(labelNum).length();
            StringBuilder currentNum = new StringBuilder(readLines.get(labelNum).substring(indexOfDoubleDot + 1, length).trim());
            putValueIntoHistoryChangesMap(labelNum, Long.parseLong(currentNum.toString()));
            long resultNum = (long) Long.parseLong(currentNum.toString()) + Long.parseLong(result.get());
            categoriesValueList.get(labelNum).setText(Long.toString(resultNum));
            StringBuilder newValue = new StringBuilder(readLines.get(labelNum).replace(currentNum.toString(), Long.toString(resultNum)));
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())))) {
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    sb.append(strLine.replace(readLines.get(labelNum), newValue)).append("\r\n");
                }
            }
            if (new File(path.toString()).exists()) {
                FileWriter fileWriter = new FileWriter(path.toString());
                fileWriter.write(sb.toString());
                fileWriter.close();
            }
            bufferedReader.close();
            fileReader.close();
            dialog.close();
        } else {
            bufferedReader.close();
            fileReader.close();
            dialog.close();
        }
    }

    public static String calculateGeneralCosts(List<Label> categoriesValueList, List<PieChart.Data> slices,
                                      List<PieChart.Data> slices2, String[] pieColors) {
        int generalCosts = 0;
        int k = 0;
        for (Label categoryValue : categoriesValueList) {
            generalCosts += Integer.parseInt(categoryValue.getText());
            k++;
            if (k == 10) {
                return Integer.toString(generalCosts);
            }
        }
        PieChartSlicesMaker.setPieChartsData(slices, slices2, categoriesValueList, pieColors);
        return Integer.toString(generalCosts);
    }

    public static String calculateGeneralIncomes(List<Label> categoriesValueList, List<PieChart.Data> slices,
                                        List<PieChart.Data> slices2, String[] pieColors) {
        int generalIncomes = 0;
        int k = 0;
        for (Label categoryValue : categoriesValueList) {
            if (k > 9) {
                generalIncomes += Integer.parseInt(categoryValue.getText());
            }
            k++;
        }
        PieChartSlicesMaker.setPieChartsData(slices, slices2, categoriesValueList, pieColors);
        return Integer.toString(generalIncomes);
    }

    public static void revertValue(List<Label> categoriesValueList, StringBuilder path) throws IOException {
        Map<Integer,Long> changesHistoryMap = CategoryValuesChangesMap.getValuesChangeMap();
        int indexOfLastUpdatedValue = (int)changesHistoryMap.keySet().toArray()[changesHistoryMap.size() - 1];
        Long previousValue = changesHistoryMap.get(indexOfLastUpdatedValue);
        categoriesValueList.get(indexOfLastUpdatedValue).setText(String.valueOf(previousValue));
        rewriteCategoryValue(indexOfLastUpdatedValue, previousValue, path, categoriesValueList);
        changesHistoryMap.remove(indexOfLastUpdatedValue);
    }

    private static void rewriteCategoryValue(int labelNum, Long value, StringBuilder path,
                                             List<Label> categoriesValueList) throws IOException {
        FileReader fileReader = new FileReader(path.toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> readLines = new ArrayList<String>();

        String fileStr1;
        while ((fileStr1 = bufferedReader.readLine()) != null) {
            readLines.add(fileStr1);
        }
        int indexOfDoubleDot = readLines.get(labelNum).indexOf(":");
        int length = readLines.get(labelNum).length();
        StringBuilder currentNum = new StringBuilder(readLines.get(labelNum).substring(indexOfDoubleDot + 1, length).trim());
        putValueIntoHistoryChangesMap(labelNum, Long.parseLong(currentNum.toString()));
        categoriesValueList.get(labelNum).setText(Long.toString(value));
        StringBuilder newValue = new StringBuilder(readLines.get(labelNum).replace(currentNum.toString(), Long.toString(value)));
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                sb.append(strLine.replace(readLines.get(labelNum), newValue)).append("\r\n");
            }
        }
        if (new File(path.toString()).exists()) {
            FileWriter fileWriter = new FileWriter(path.toString());
            fileWriter.write(sb.toString());
            fileWriter.close();
        }
        bufferedReader.close();
        fileReader.close();
    }

}
