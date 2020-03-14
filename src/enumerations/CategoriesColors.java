package enumerations;

import java.util.ArrayList;
import java.util.List;

public enum CategoriesColors {
    SUPERMARKETS_COLOR("-fx-pie-color: #69b3e9"),
    BEAUTY_AND_HEALTH_COLOR("-fx-pie-color: #E179AB"),
    HOUSE_AND_REPAIR_COLOR("-fx-pie-color: #488147"),
    TRANSPORT_COLOR("-fx-pie-color: #90643C"),
    CLOTHES_AND_SHOES_COLOR("-fx-pie-color: #D7DB28"),
    ENTERTAINMENT_COLOR("-fx-pie-color: #8E5EB2"),
    GIFTS_COLOR("-fx-pie-color: #1C3985"),
    STATE_SERVICES_COLOR("-fx-pie-color: #993E49"),
    ZHKU_COLOR("-fx-pie-color: #479E84"),
    ANOTHER_COSTS_COLOR("-fx-pie-color: #598487"),
    SALARY_COLOR("-fx-pie-color: #4DBBBB"),
    BUSINESS_COLOR("-fx-pie-color: #3A9348"),
    INVEST_COLOR("-fx-pie-color: #1C3985"),
    DEPOSIT_COLOR("-fx-pie-color: #BB4D88"),
    SOCIAL_PAYMENTS_COLOR("-fx-pie-color: #5E2F37"),
    ANOTHER_INCOMES_COLOR("-fx-pie-color: #AC4F60");

    private String colorFxml;

    CategoriesColors(String fxmlCode) {
        this.colorFxml = fxmlCode;
    }

    public String getColorFxml() {
        return colorFxml;
    }

    public static List<String> getColorList() {
        List<String> colorList = new ArrayList<>();

        return colorList;
    }
}
