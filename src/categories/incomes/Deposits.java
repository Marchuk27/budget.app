package categories.incomes;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class Deposits extends AbstractCategory {

    private Deposits() {}

    private static Deposits instance;

    public static Deposits getInstance() {
        if (instance == null) {
            instance = new Deposits();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.DEPOSIT);
        instance.setFieldToTxtFile(CategoriesTextForFiles.DEPOSIT_TXT);
        instance.setCategoryColor(CategoriesColors.DEPOSIT_COLOR);
    }
}
