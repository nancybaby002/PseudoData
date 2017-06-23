package utils;

/**
 * Created by adinlead on 17-6-22.
 */
public class TextUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(Object obj) {
        return isEmpty(stringOf(obj));
    }

    public static boolean notEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean notEmpty(Object obj) {
        return notEmpty(stringOf(obj));
    }

    public static String stringOf(Object obj) {
        return obj == null ? null : obj.toString();
    }
}
