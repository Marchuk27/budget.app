package categories.incomes;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class Business extends AbstractCategory {

    private Business() {}

    private static Business instance;

    public static Business getInstance() {
        if (instance == null) {
            instance = new Business();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.BUSINESS);
        instance.setFieldToTxtFile(CategoriesTextForFiles.BUSINESS_TXT);
        instance.setCategoryColor(CategoriesColors.BUSINESS_COLOR);
    }
}
