package jp.cafebabe.fritter.validators.impl.nostatic;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.Type;
import jp.cafebabe.fritter.validators.impl.Utils;

import java.util.Objects;

class AllStaticState {
    private boolean allStatic = true;
    private SimpleName name;

    public AllStaticState(SimpleName name) {
        this.name = name;
    }

    public boolean isConstructingMethod(MethodDeclaration node) {
        Type type = node.getType();
        return Utils.isStatic(node)
                && Objects.equals(name.toString(),
                type.toString());
    }

    public boolean checkAllStatic(MethodDeclaration node) {
        return checkState(Utils.isMainMethod(node),
                isConstructingMethod(node),
                Utils.isStatic(node));
    }

    private boolean checkState(boolean main, boolean constructor, boolean staticMethod) {
        return Flag.of(!main && !constructor && staticMethod)
                .then(value -> allStatic = false)
                .value();
    }

    public void ng(Runnable runner) {
        if(!allStatic)
            runner.run();
    }
}
