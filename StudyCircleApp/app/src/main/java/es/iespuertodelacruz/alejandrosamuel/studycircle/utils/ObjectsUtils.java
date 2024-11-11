package es.iespuertodelacruz.alejandrosamuel.studycircle.utils;

import java.util.Arrays;
import java.util.Objects;

public class ObjectsUtils {
    public static boolean notNullNorEmpty(Object... objects) {
        if(Arrays.stream(objects).noneMatch(Objects::isNull))
            return Arrays.stream(objects).filter(o -> o instanceof String).noneMatch(o -> ((String) o).trim().isEmpty());

        return false;
    }
}
