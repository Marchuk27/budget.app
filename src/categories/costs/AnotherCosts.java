package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class AnotherCosts extends AbstractCategory {

    private AnotherCosts() {}

    private static AnotherCosts instance;

    public static AnotherCosts getInstance() {
        if (instance == null) {
            instance = new AnotherCosts();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.ANOTHER_COSTS);
        instance.setFieldToTxtFile(CategoriesTextForFiles.ANOTH_COST_TXT);
        instance.setCategoryColor(CategoriesColors.ANOTHER_COSTS_COLOR);
    }
}
