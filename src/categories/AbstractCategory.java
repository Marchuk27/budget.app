package categories;

import enumerations.CategoriesColors;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCategory {
    private String categoryName;
    private String fieldToTxtFile;
    private CategoriesColors categoryColor;
    private Label categoryLabel;

}
