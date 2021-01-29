package jp.cafebabe.fritter.validators.impl.fcc;

import org.eclipse.jdt.core.dom.Type;

import java.util.ArrayList;
import java.util.List;

class CollectionTypeChecker {
    private static final List<String> COLLECTIONS = new ArrayList<>();

    static {
        COLLECTIONS.add("(java.util.)?([A-Z][a-zA-Z0-9]+)?List(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-zA-Z0-9]+)?Set(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-zA-Z0-9]+)?Map(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-zA-Z0-9]+)?Deque(<.*>)?");
        COLLECTIONS.add("(java.util.)?([A-Z][a-zA-Z0-9]+)?Queue(<.*>)?");
    }

    public static boolean isCollection(Type fieldType) {
        String typeString = fieldType.toString();
        return COLLECTIONS.stream()
                .anyMatch(typeString::matches);
    }
}
