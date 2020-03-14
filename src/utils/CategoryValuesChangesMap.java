package utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class CategoryValuesChangesMap {
    private static final Map<Integer, Long> valuesChangeMap = new LinkedHashMap<>();

    public static Map<Integer, Long> getValuesChangeMap() {
        return valuesChangeMap;
    }

    public static void putValueIntoHistoryChangesMap(Integer labelNum, Long value) {
        if (valuesChangeMap.containsKey(labelNum)) {
            valuesChangeMap.remove(labelNum);
        }
        valuesChangeMap.put(labelNum, value);
    }
}
