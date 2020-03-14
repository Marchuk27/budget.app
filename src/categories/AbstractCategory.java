package categories;

import enumerations.CategoriesColors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCategory {
    private String categoryName;
    private String fieldToTxtFile;
    private CategoriesColors categoryColor;
}
