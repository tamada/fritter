package jp.cafebabe.fritter.utils;

public class CamelCaseUtils {
    public static final String upperCamelCase(String name) {
        return new StringBuilder().append(firstUpperChar(name))
                .append(name.substring(1))
                .toString();
    }

    public static final String lowerCamelCase(String name) {
        return new StringBuilder().append(firstLowerChar(name))
                .append(name.substring(1))
                .toString();
    }

    private static final char firstUpperChar(String str) {
        return str.toUpperCase()
                .charAt(0);
    }

    private static final char firstLowerChar(String str) {
        return str.toLowerCase()
                .charAt(0);
    }

    public static final String[] split(String camelCaseString) {
        // http://akisute3.hatenablog.com/entry/20111217/1324109628
        return camelCaseString.split("(?<=[A-Z])(?=[A-Z][a-z])|(?<=[a-z])(?=[A-Z])");
    }
}
