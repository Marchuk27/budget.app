package categories.incomes;
import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import enumerations.CategoriesColors;

public class AnotherIncomes extends AbstractCategory {

    private AnotherIncomes() {}

    private static AnotherIncomes instance;

    public static AnotherIncomes getInstance() {
        if (instance == null) {
            instance = new AnotherIncomes();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName("Прочие доходы");
        instance.setFieldToTxtFile(CategoriesTextForFiles.ANOTH_INCOME_TXT);
        instance.setCategoryColor(CategoriesColors.ANOTHER_INCOMES_COLOR);
    }
}

