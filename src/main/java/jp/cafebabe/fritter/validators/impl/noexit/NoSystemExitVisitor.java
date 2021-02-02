package jp.cafebabe.fritter.validators.impl.noexit;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.DeclarationsUtils;

import java.util.Objects;
import java.util.Optional;

class NoSystemExitVisitor extends FritterASTVisitor {
    private static final Message MESSAGE = Message.format("no System.exit except main method");

    public NoSystemExitVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        if(!DeclarationsUtils.isMainMethod(node))
            super.visit(node, violations);
    }

    @Override
    public void visit(MethodCallExpr node, Violations violations) {
        if (isSystemExitCall(node))
            violations.add(createViolation(node, MESSAGE));
    }

    private boolean isSystemExitCall(MethodCallExpr node) {
        return DeclarationsUtils.isName(node.getName(), "exit") &&
                isSystem(node.getScope());
    }

    private boolean isSystem(Optional<Expression> expression) {
        return expression
                .map(exp -> Objects.equals(exp.toString(), "System"))
                .orElse(false);
    }
}
