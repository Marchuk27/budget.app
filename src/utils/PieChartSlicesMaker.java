package utils;

import constants.ColorValues;
import javafx.scene.chart.PieChart;

import java.util.Arrays;
import java.util.List;
import constants.PieChartCategoriesConstants;
import javafx.scene.control.Label;

public class PieChartSlicesMaker extends PieChartCategoriesConstants {
    public static PieChart.Data slice1 = new PieChart.Data(SUPERMARKETS_DATA, 0);
    public static PieChart.Data slice2 = new PieChart.Data(BEAUTY_AND_HEALTH_DATA, 0);
    public static PieChart.Data slice3 = new PieChart.Data(HOUSE_AND_REPAIR_DATA, 0);
    public static PieChart.Data slice4 = new PieChart.Data(TRANSPORT_DATA, 0);
    public static PieChart.Data slice5 = new PieChart.Data(CLOTHES_AND_SHOES_DATA, 0);
    public static PieChart.Data slice6 = new PieChart.Data(ENTERTAINMENT_DATA, 0);
    public static PieChart.Data slice7 = new PieChart.Data(GIFTS_DATA, 0);
    public static PieChart.Data slice8 = new PieChart.Data(STATE_SERVICES_DATA, 0);
    public static PieChart.Data slice9 = new PieChart.Data(ZHKU_DATA, 0);
    public static PieChart.Data slice10 = new PieChart.Data(ANOTHER_COSTS_DATA, 0);
    public static PieChart.Data slice11 = new PieChart.Data(SALARY_DATA, 0);
    public static PieChart.Data slice12 = new PieChart.Data(BUSINESS_DATA, 0);
    public static PieChart.Data slice13 = new PieChart.Data(INVEST_DATA, 0);
    public static PieChart.Data slice14 = new PieChart.Data(DEPOSIT_DATA, 0);
    public static PieChart.Data slice15 = new PieChart.Data(SOCIAL_PAYMENTS_DATA, 0);
    public static PieChart.Data slice16 = new PieChart.Data(ANOTHER_INCOMES_DATA, 0);

    public static void fillingColorsArray(String[] colors) {
        colors[0] = ColorValues.SUPERMARKETS_COLOR;
        colors[1] = ColorValues.BEAUTY_AND_HEALTH_COLOR;
        colors[2] = ColorValues.HOUSE_AND_REPAIR_COLOR;
        colors[3] = ColorValues.TRANSPORT_COLOR;
        colors[4] = ColorValues.CLOTHES_AND_SHOES_COLOR;
        colors[5] = ColorValues.ENTERTAINMENT_COLOR;
        colors[6] = ColorValues.GIFTS_COLOR;
        colors[7] = ColorValues.STATE_SERVICES_COLOR;
        colors[8] = ColorValues.ZHKU_COLOR;
        colors[9] = ColorValues.ANOTHER_COSTS_COLOR;
        colors[10] = ColorValues.SALARY_COLOR;
        colors[11] = ColorValues.BUSINESS_COLOR;
        colors[12] = ColorValues.INVEST_COLOR;
        colors[13] = ColorValues.DEPOSIT_COLOR;
        colors[14] = ColorValues.SOCIAL_PAYMENTS_COLOR;
        colors[15] = ColorValues.ANOTHER_INCOMES_COLOR;
    }

    public static void addSlicesToLists(List<PieChart.Data> slices, List<PieChart.Data> slices2, PieChart pc1, PieChart pc2) {
        slices.addAll(Arrays.asList(slice1, slice2, slice3, slice4, slice5, slice6, slice7, slice8, slice9, slice10));
        slices2.addAll(Arrays.asList(slice11, slice12, slice13, slice14, slice15, slice16));

        pc1.getData().addAll(slices);
        pc1.setLegendVisible(false);
        pc2.getData().addAll(slices2);
        pc2.setLegendVisible(false);
    }

    public static void setPieChartsData(List<PieChart.Data> slices, List<PieChart.Data> slices2,
                                  List<Label> categoriesValueList, String[] colors) {
        int i = 0;
        for (PieChart.Data slice : slices) {
            slice.setPieValue(Double.parseDouble(categoriesValueList.get(i).getText()));
            slice.getNode().setStyle(colors[i]);
            i++;
        }
        for (PieChart.Data slice : slices2) {
            slice.setPieValue(Double.parseDouble(categoriesValueList.get(i).getText()));
            slice.getNode().setStyle(colors[i]);
            i++;
        }

    }
}
