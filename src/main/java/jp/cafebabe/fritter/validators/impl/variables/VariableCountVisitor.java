package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

class VariableCountVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "local variable count is %d, more than %s";
    private int localVariableCount = 0;

    public VariableCountVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        localVariableCount = 0;
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
        localVariableCount++;
        return super.visit(node);
    }

    @Override
    public void endVisit(MethodDeclaration node) {
        if(parameter().lessThan(Value.of(localVariableCount)))
            add(node, Message.format(FORMATTER, localVariableCount, parameter()));
    }

}
