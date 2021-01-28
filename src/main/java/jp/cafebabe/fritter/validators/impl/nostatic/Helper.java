package jp.cafebabe.fritter.validators.impl.nostatic;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Type;

import java.util.Objects;

class Helper {
    public static boolean isConstructingMethod(MethodDeclaration node, SimpleName name) {
        Type type = node.getReturnType2();
        return Objects.equals(name.toString(),
                type.toString());
    }
}
