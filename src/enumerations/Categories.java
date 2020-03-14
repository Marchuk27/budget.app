package enumerations;

import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.PieChartSlicesMaker.*;
import static constants.CategoriesTextForFiles.*;

public enum Categories {
    SUPERMARKETS(1, slice1, SUPERMARKET_TXT),
    BEAUTY_AND_HEALTH(2, slice2, BEAUTY_HEALTH_TXT),
    HOUSE_AND_REPAIRS(3, slice3, HOUSE_REPAIR_TXT),
    TRANSPORT(4, slice4, TRANSPORT_TXT),
    CLOTHES_AND_SHOES(5, slice5, CLOTHES_SHOES_TXT),
    ENTERTAINMENTS(6, slice6, ENTERTAINMENT_TXT),
    GIFTS(7, slice7, GIFT_TXT),
    STATE_SERVICES(8, slice8, STATE_SERVICE_TXT),
    ZHKU_USLUGI_SVYAZI(9, slice9, ZHKU_TXT),
    ANOTHER_COSTS(10, slice10, ANOTH_COST_TXT),
    SALARY(11, slice11, SALARY_TXT),
    BUSINESS(12, slice12, BUSINESS_TXT),
    INVEST(13, slice13, INVEST_TXT),
    DEPOSIT(14, slice14, DEPOSIT_TXT),
    SOCIAL_PAYMENTS(15, slice15, SOCIAL_TXT),
    ANOTHER_INCOMES(16, slice16, ANOTH_INCOME_TXT);

    private int categoryCode;
    private PieChart.Data pieChartSlice;
    private String textForTxtFiles;

    Categories(int categoryCode, PieChart.Data pieChartSlice,  String textForFiles) {
        this.categoryCode = categoryCode;
        this.pieChartSlice = pieChartSlice;
        this.textForTxtFiles = textForFiles;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public PieChart.Data getPieChartSlice() {
        return pieChartSlice;
    }

    public String getTextForTxt() {
        return textForTxtFiles;
    }
}
