package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class ZhkuAndNet extends AbstractCategory {

    private ZhkuAndNet() {}

    private static ZhkuAndNet instance;

    public static ZhkuAndNet getInstance() {
        if (instance == null) {
            instance = new ZhkuAndNet ();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.ZHKU);
        instance.setFieldToTxtFile(CategoriesTextForFiles.ZHKU_TXT);
        instance.setCategoryColor(CategoriesColors.ZHKU_COLOR);
    }

}
