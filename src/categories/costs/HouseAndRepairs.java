package categories.costs;

import categories.AbstractCategory;
import categories.incomes.AnotherIncomes;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
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
        instance.setCategoryName(CategoryNames.HOUSE_AND_REPAIR);
        instance.setFieldToTxtFile(CategoriesTextForFiles.HOUSE_REPAIR_TXT);
        instance.setCategoryColor(CategoriesColors.HOUSE_AND_REPAIR_COLOR);
    }
}
