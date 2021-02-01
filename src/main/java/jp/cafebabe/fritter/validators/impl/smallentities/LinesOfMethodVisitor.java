package jp.cafebabe.fritter.validators.impl.smallentities;

import com.github.javaparser.ast.body.MethodDeclaration;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;

class LinesOfMethodVisitor extends LinesOfCodeVisitor {
    public LinesOfMethodVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        checkViolation(node, "method", violations);
        super.visit(node, violations);
    }
}