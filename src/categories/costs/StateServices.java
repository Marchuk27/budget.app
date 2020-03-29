package categories.costs;

import categories.AbstractCategory;
import constants.CategoriesTextForFiles;
import constants.CategoryNames;
import enumerations.CategoriesColors;

public class StateServices extends AbstractCategory {

    private StateServices() {}

    private static StateServices instance;

    public static StateServices getInstance() {
        if (instance == null) {
            instance = new StateServices ();
            setInstanceFields();
        }
        return instance;
    }

    private static void setInstanceFields() {
        instance.setCategoryName(CategoryNames.STATE);
        instance.setFieldToTxtFile(CategoriesTextForFiles.STATE_SERVICE_TXT);
        instance.setCategoryColor(CategoriesColors.STATE_SERVICES_COLOR);
    }

}
