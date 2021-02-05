package jp.cafebabe.fritter.config;

public enum CheckerType {
    INDENT_LEVEL,
    NO_ELSE,
    PRIMITIVE_WRAPPING,
    DOT_COUNT_PER_LINE,
    NO_ABBREV,
    LINES_OF_CLASS,
    LINES_OF_METHOD,
    CLASSES_IN_PACKAGE,
    FIELD_COUNT,
    FIRST_CLASS_COLLECTION,
    NO_ACCESSOR,
    VARIABLE_COUNT,
    NO_STATIC_METHOD,
    NO_NEW_ARRAY,
    NO_SYSTEM_EXIT,
    NO_RETURN_CODE_IN_PRINTF,
    SINGLE_CHARACTER_NAME;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static CheckerType of(String name){
        return CheckerType.valueOf(name.toUpperCase());
    }
}
