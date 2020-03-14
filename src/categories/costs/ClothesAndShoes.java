package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import enumerations.CategoriesColors;

public class ClothesAndShoes extends AbstractCategory {

    private ClothesAndShoes() {}

    private static ClothesAndShoes instance;

    public static ClothesAndShoes getInstance() {
        if (instance == null) {
            instance = new ClothesAndShoes ();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName("Одежда и обувь");
        instance.setFieldToTxtFile(CategoriesTextForFiles.CLOTHES_SHOES_TXT);
        instance.setCategoryColor(CategoriesColors.CLOTHES_AND_SHOES_COLOR);
    }
}

