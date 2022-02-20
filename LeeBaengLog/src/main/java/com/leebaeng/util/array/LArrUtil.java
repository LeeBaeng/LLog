package com.leebaeng.util.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * List And Array util
 *
 * @author LeeBaeng
 */
public class LArrUtil {
    public static Object[] convertObjectToArray(Object array) {
        if (array.getClass().isArray()) {
            Class ofArray = array.getClass().getComponentType();
            if (ofArray != null && ofArray.isPrimitive()) {
                List ar = new ArrayList();
                int length = Array.getLength(array);
                for (int i = 0; i < length; i++) {
                    ar.add(Array.get(array, i));
                }
                return ar.toArray();
            } else {
                return (Object[]) array;
            }
        } else
            return new Object[]{array};
    }
}
