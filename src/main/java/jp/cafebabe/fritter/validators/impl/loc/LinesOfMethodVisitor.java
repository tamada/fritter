package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.validators.Validator;
import org.eclipse.jdt.core.dom.MethodDeclaration;

class LinesOfMethodVisitor extends LinesOfCodeVisitor {
    public LinesOfMethodVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodDeclaration type) {
        checkViolation(type, "method");
        return super.visit(type);
    }
}