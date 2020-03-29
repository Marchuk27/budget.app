package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class Transport extends AbstractCategory {

    private Transport() {}

    private static Transport instance;

    public static Transport getInstance() {
        if (instance == null) {
            instance = new Transport ();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.TRANSPORT);
        instance.setFieldToTxtFile(CategoriesTextForFiles.TRANSPORT_TXT);
        instance.setCategoryColor(CategoriesColors.TRANSPORT_COLOR);
    }
}
