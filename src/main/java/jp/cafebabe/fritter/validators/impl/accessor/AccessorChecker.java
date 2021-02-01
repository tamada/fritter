package jp.cafebabe.fritter.validators.impl.accessor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.stream.Stream;

class AccessorChecker {
    private static final Modifier PUBLIC = Modifier.publicModifier();

    public boolean isPublicMethod(MethodDeclaration node){
        return stream(node).filter(PUBLIC::equals)
                .count() > 0;
    }

    private Stream<Modifier> stream(MethodDeclaration node) {
        return node.getModifiers()
                .stream();
    }

    public boolean isTarget(MethodDeclaration node, String pattern) {
        String name = node.getNameAsString();
        return isPublicMethod(node)
                && name.matches(pattern);
    }
}
