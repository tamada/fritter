package jp.cafebabe.fritter.validators.impl;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;
import com.github.javaparser.ast.type.Type;

import java.util.List;
import java.util.Objects;

public class DeclarationsUtils {
    public static boolean isStatic(NodeWithModifiers<?> node) {
        List<Modifier> modifiers = node.getModifiers();
        return modifiers.contains(Modifier.staticModifier());
    }

    public static boolean isReturnVoid(MethodDeclaration node) {
        Type type = node.getType();
        return type.isVoidType();
    }

    public static boolean isMainMethod(MethodDeclaration node) {
        return isReturnVoid(node) && isStatic(node)
                && isName(node.getName(), "main") && isArgumentsStringArray(node);
    }

    public static boolean isName(SimpleName simpleName, String name) {
        return Objects.equals(name,
                simpleName.getIdentifier());
    }

    public static boolean isArgumentsStringArray(MethodDeclaration node) {
        NodeList<Parameter> list = node.getParameters();
        return list.size() == 1 && isArrayString(list.get(0));
    }

    public static boolean isArrayString(Parameter node) {
        String str = node.getType().asString();
        return Objects.equals(str, "String[]")
                || Objects.equals(str, "String...");
    }
}
