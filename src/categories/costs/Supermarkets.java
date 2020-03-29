package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class Supermarkets extends AbstractCategory {

    private Supermarkets () {}

    private static Supermarkets instance;

    public static Supermarkets getInstance() {
        if (instance == null) {
            instance = new Supermarkets();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.SUPERMARKETS);
        instance.setFieldToTxtFile(CategoriesTextForFiles.SUPERMARKET_TXT);
        instance.setCategoryColor(CategoriesColors.SUPERMARKETS_COLOR);
    }
}
