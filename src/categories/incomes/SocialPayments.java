package categories.incomes;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class SocialPayments extends AbstractCategory {

    private SocialPayments() {}

    private static SocialPayments instance;

    public static SocialPayments getInstance() {
        if (instance == null) {
            instance = new SocialPayments();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.SOCIAL);
        instance.setFieldToTxtFile(CategoriesTextForFiles.SOCIAL_TXT);
        instance.setCategoryColor(CategoriesColors.SOCIAL_PAYMENTS_COLOR);
    }
}
