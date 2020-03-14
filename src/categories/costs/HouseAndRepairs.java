package categories.costs;

import categories.AbstractCategory;
import categories.incomes.AnotherIncomes;
import constants.CategoriesTextForFiles;
import enumerations.CategoriesColors;

public class HouseAndRepairs extends AbstractCategory {

    private HouseAndRepairs() {}

    private static HouseAndRepairs instance;

    public static HouseAndRepairs getInstance() {
        if (instance == null) {
            instance = new HouseAndRepairs();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName("Дом и ремонт");
        instance.setFieldToTxtFile(CategoriesTextForFiles.HOUSE_REPAIR_TXT);
        instance.setCategoryColor(CategoriesColors.HOUSE_AND_REPAIR_COLOR);
    }
}
