package categories.incomes;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import enumerations.CategoriesColors;

public class Salary extends AbstractCategory {

    private Salary() {}

    private static Salary instance;

    public static Salary getInstance() {
        if (instance == null) {
            instance = new Salary();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName("Зарплата");
        instance.setFieldToTxtFile(CategoriesTextForFiles.SALARY_TXT);
        instance.setCategoryColor(CategoriesColors.SALARY_COLOR);
    }
}
