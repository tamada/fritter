package jp.cafebabe.fritter.validators.impl;

import org.eclipse.jdt.core.dom.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Utils {
    public static boolean isStatic(MethodDeclaration node) {
        int modifier = node.getModifiers();
        return Modifier.isStatic(modifier);
    }

    public static boolean isReturnVoid(MethodDeclaration node) {
        Type type = node.getReturnType2();
        return type.isPrimitiveType()
                && ((PrimitiveType)type).getPrimitiveTypeCode() == PrimitiveType.VOID;
    }

    public static boolean isName(SimpleName simpleName, String name) {
        return Objects.equals(name,
                simpleName.getIdentifier());
    }

    public static boolean isArgumentsStringArray(MethodDeclaration node) {
        List list = node.parameters();
        return list.size() == 1 && isArrayString((SingleVariableDeclaration)list.get(0));
    }

    public static boolean isArrayString(SingleVariableDeclaration node) {
        var str = Optional.ofNullable(node.getType())
                .map(type -> type.toString())
                .orElse("");
        return Objects.equals(str, "String[]") || Objects.equals(str, "String...");
    }
}
