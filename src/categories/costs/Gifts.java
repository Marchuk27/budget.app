package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class Gifts extends AbstractCategory {

    private Gifts() {}

    private static Gifts instance;

    public static Gifts getInstance() {
        if (instance == null) {
            instance = new Gifts();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.GIFTS);
        instance.setFieldToTxtFile(CategoriesTextForFiles.GIFT_TXT);
        instance.setCategoryColor(CategoriesColors.GIFTS_COLOR);
    }
}
