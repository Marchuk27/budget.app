package categories.incomes;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import enumerations.CategoriesColors;

public class Investments extends AbstractCategory {

    private Investments() {}

    private static Investments instance;

    public static Investments getInstance() {
        if (instance == null) {
            instance = new Investments();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName("Инвестиции");
        instance.setFieldToTxtFile(CategoriesTextForFiles.INVEST_TXT);
        instance.setCategoryColor(CategoriesColors.INVEST_COLOR);
    }
}
