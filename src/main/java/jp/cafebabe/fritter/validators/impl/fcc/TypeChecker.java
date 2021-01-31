package jp.cafebabe.fritter.validators.impl.fcc;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.Type;

import java.util.List;
import java.util.function.Predicate;

class TypeChecker {
    private static final List<String> COLLECTIONS = List.of("(java.util.)?([A-Z][a-zA-Z0-9]+)?List(<.*>)?",
            "(java.util.)?([A-Z][a-zA-Z0-9]+)?Set(<.*>)?", "(java.util.)?([A-Z][a-zA-Z0-9]+)?Map(<.*>)?",
            "(java.util.)?([A-Z][a-zA-Z0-9]+)?Deque(<.*>)?", "(java.util.)?([A-Z][a-zA-Z0-9]+)?Queue(<.*>)?");

    private static final List<String> PRIMITIVES = List.of("byte", "short", "int", "long", "float",
            "char", "double", "boolean", "(java.lang.)?String", "(java.util.)?Date");

    public static boolean isPrimitive(FieldDeclaration node) {
        return check(node, TypeChecker::isPrimitive);
    }

    public static boolean isCollection(FieldDeclaration node) {
        return check(node, TypeChecker::isCollection);
    }

    private static boolean check(FieldDeclaration node, Predicate<VariableDeclarator> predicate) {
        return node.getVariables()
                .stream().filter(predicate::test)
                .count() > 0;
    }

    private static boolean contains(Type targetType, List<String> types) {
        return types.stream()
                .filter(type -> match(targetType, type))
                .count() > 0;
    }

    private static boolean isPrimitive(VariableDeclarator node) {
        return contains(node.getType(), PRIMITIVES);
    }

    private static boolean match(Type type, String typeRegexp) {
        return type.toString()
                .matches(typeRegexp);
    }

    private static boolean isCollection(VariableDeclarator node) {
        Type type = node.getType();
        return type.isArrayType()
                || contains(type, COLLECTIONS);
    }
}
