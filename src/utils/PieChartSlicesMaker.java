package utils;

import categories.AbstractCategory;
import javafx.scene.chart.PieChart;

import java.util.Arrays;
import java.util.List;
import constants.CategoryNames;
import javafx.scene.control.Label;

public class PieChartSlicesMaker extends CategoryNames {
    public static PieChart.Data slice1 = new PieChart.Data(SUPERMARKETS, 0);
    public static PieChart.Data slice2 = new PieChart.Data(BEAUTY_AND_HEALTH, 0);
    public static PieChart.Data slice3 = new PieChart.Data(HOUSE_AND_REPAIR, 0);
    public static PieChart.Data slice4 = new PieChart.Data(TRANSPORT, 0);
    public static PieChart.Data slice5 = new PieChart.Data(CLOTHES_AND_SHOES, 0);
    public static PieChart.Data slice6 = new PieChart.Data(ENTERTAINMENT, 0);
    public static PieChart.Data slice7 = new PieChart.Data(GIFTS, 0);
    public static PieChart.Data slice8 = new PieChart.Data(STATE, 0);
    public static PieChart.Data slice9 = new PieChart.Data(ZHKU, 0);
    public static PieChart.Data slice10 = new PieChart.Data(ANOTHER_COSTS, 0);
    public static PieChart.Data slice11 = new PieChart.Data(SALARY, 0);
    public static PieChart.Data slice12 = new PieChart.Data(BUSINESS, 0);
    public static PieChart.Data slice13 = new PieChart.Data(INVEST, 0);
    public static PieChart.Data slice14 = new PieChart.Data(DEPOSIT, 0);
    public static PieChart.Data slice15 = new PieChart.Data(SOCIAL, 0);
    public static PieChart.Data slice16 = new PieChart.Data(ANOTHER_INCOMES, 0);

    public static void fillingColorsArray(String[] colors) {
        int i = 0;
        for (AbstractCategory category : CategoriesUtils.getCategoriesAsList()) {
            colors[i] = category.getCategoryColor().getColorFxml();
            i++;
        }
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
