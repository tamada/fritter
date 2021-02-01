package jp.cafebabe.fritter.validators.impl.variables;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

class LocalVariableCountVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "local variable count is %d, more than %s";
    private int localVariableCount = 0;

    public LocalVariableCountVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        localVariableCount = 0;
        super.visit(node, violations);
        checkViolation(node, violations);
    }

    @Override
    public void visit(VariableDeclarationExpr node, Violations violations) {
        localVariableCount++;
        super.visit(node, violations);
    }

    private void checkViolation(MethodDeclaration node, Violations violations) {
        if(parameter().lessThan(Value.of(localVariableCount)))
            violations.add(createViolation(node,
                    Message.format(FORMATTER, localVariableCount, parameter())));
    }

}
