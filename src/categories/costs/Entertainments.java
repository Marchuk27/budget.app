package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class Entertainments extends AbstractCategory {

    private Entertainments() {}

    private static Entertainments instance;

    public static Entertainments getInstance() {
        if (instance == null) {
            instance = new Entertainments();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.ENTERTAINMENT);
        instance.setFieldToTxtFile(CategoriesTextForFiles.ENTERTAINMENT_TXT);
        instance.setCategoryColor(CategoriesColors.ENTERTAINMENT_COLOR);
    }
}
