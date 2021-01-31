package jp.cafebabe.fritter.validators.impl.accessor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

public class NoAccessorVisitor extends FritterASTVisitor {
    private AccessorChecker checker = new AccessorChecker();

    public NoAccessorVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        checkViolation(node, violations);
        super.visit(node, violations);
    }

    private String getMethodName(MethodDeclaration node) {
        SimpleName name = node.getName();
        return name.getIdentifier();
    }

    private void checkViolation(MethodDeclaration node, Violations violations) {
        checkViolationImpl(violations, node, "get[A-Z][a-zA-Z0-9]*$", "%s: no getter method");
        checkViolationImpl(violations, node, "get[A-Z][a-zA-Z0-9]*$", "%s: no getter method");
    }

    private void checkViolationImpl(Violations violations, MethodDeclaration node, String pattern, String formatter) {
        if(checker.isTarget(node, pattern))
            addTarget(violations, createViolation(node, Message.format(formatter, node.getNameAsString())));
    }

    private void addTarget(Violations violations, Violation violation) {
        violations.add(violation);
    }
}