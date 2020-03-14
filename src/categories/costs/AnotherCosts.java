package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
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
        instance.setCategoryName("Прочие расходы");
        instance.setFieldToTxtFile(CategoriesTextForFiles.ANOTH_COST_TXT);
        instance.setCategoryColor(CategoriesColors.ANOTHER_COSTS_COLOR);
    }
}
