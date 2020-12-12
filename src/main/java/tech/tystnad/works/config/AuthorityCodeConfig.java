package tech.tystnad.works.config;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Set;

public final class AuthorityCodeConfig {
    private static final ResourceBundle AUTHORITY;

    static {
        AUTHORITY = ResourceBundle.getBundle("authority");
    }

    public static Set<String> keySet() {
        return AUTHORITY.keySet();
    }

    public static Enumeration<String> getKeys() {
        return AUTHORITY.getKeys();
    }

    public static String getString(String key) {
        return AUTHORITY.getString(key);
    }

    public static String[] getStringArray(String key) {
        return AUTHORITY.getStringArray(key);
    }

    public static Object getObject(String key) {
        return AUTHORITY.getObject(key);
    }

    public static boolean containsKey(String key) {
        return AUTHORITY.containsKey(key);
    }
}
