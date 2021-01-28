package jp.cafebabe.fritter.validators.impl.noexit;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.util.Objects;

class NoSystemExitVisitor extends FritterASTVisitor {
    private static final Message MESSAGE = Message.format("no System.exit except main method");

    public NoSystemExitVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        return !Utils.isMainMethod(node);
    }

    public boolean visit(MethodInvocation node) {
        if (isSystemExitCall(node))
            add(node, MESSAGE);
        return true;
    }

    private boolean isSystemExitCall(MethodInvocation node) {
        return Utils.isName(node.getName(), "exit") &&
                isSystem(node.getExpression());
    }

    private boolean isSystem(Expression expression) {
        return Objects.equals(expression.toString(), "System");
    }
}
