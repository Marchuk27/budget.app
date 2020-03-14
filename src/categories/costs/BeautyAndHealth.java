package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import enumerations.CategoriesColors;

public class BeautyAndHealth extends AbstractCategory {

    private BeautyAndHealth() {}

    private static BeautyAndHealth instance;

    public static BeautyAndHealth getInstance() {
        if (instance == null) {
            instance = new BeautyAndHealth ();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName("Красота и здоровье");
        instance.setFieldToTxtFile(CategoriesTextForFiles.BEAUTY_HEALTH_TXT);
        instance.setCategoryColor(CategoriesColors.BEAUTY_AND_HEALTH_COLOR);
    }

}
