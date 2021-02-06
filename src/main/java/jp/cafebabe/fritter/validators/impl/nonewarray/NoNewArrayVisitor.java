package jp.cafebabe.fritter.validators.impl.nonewarray;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

class NoNewArrayVisitor extends FritterASTVisitor {
    private static final Message MESSAGE = Message.format("do not use array, use List instead");

    public NoNewArrayVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> super.visit(n, v));
    }

    @Override
    public void visit(FieldDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> super.visit(n, v));
    }

    @Override
    public void visit(ArrayCreationExpr node, Violations violations) {
        violations.add(createViolation(node, MESSAGE));
        super.visit(node, violations);
    }

    @Override
    public void visit(ArrayInitializerExpr node, Violations violations) {
        violations.add(createViolation(node, MESSAGE));
        super.visit(node, violations);
    }
}
