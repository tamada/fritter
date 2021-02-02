package jp.cafebabe.fritter.validators.impl.accessor;

import com.github.javaparser.ast.body.MethodDeclaration;
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

    private void checkViolation(MethodDeclaration node, Violations violations) {
        checkViolationImpl(violations, node, "get[A-Z][a-zA-Z0-9]*$", "%s: no getter method");
        checkViolationImpl(violations, node, "set[A-Z][a-zA-Z0-9]*$", "%s: no setter method");
    }

    private void checkViolationImpl(Violations violations, MethodDeclaration node, String pattern, String formatter) {
        if(checker.isTarget(node, pattern))
            violations.add(create(node, formatter));
    }

    private Violation create(MethodDeclaration node, String formatter) {
        return createViolation(node, Message.format(formatter,
                node.getNameAsString()));
    }
}