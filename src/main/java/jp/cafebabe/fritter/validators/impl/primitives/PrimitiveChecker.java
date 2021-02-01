package jp.cafebabe.fritter.validators.impl.primitives;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;

import java.util.Arrays;
import java.util.List;

class PrimitiveChecker {
    private static final List<String> PRIMITIVES = Arrays.asList("byte", "short", "int", "long", "float",
            "char", "double", "boolean", "(java.lang.)?String", "(java.util.)?Date");

    public static boolean check(FieldDeclaration node) {
        Type type = node.getType();
        return check(type.toString());
    }

    private static boolean check(String typeString) {
        return PRIMITIVES.stream()
                .filter(typeString::matches)
                .count() > 0;
    }
}
