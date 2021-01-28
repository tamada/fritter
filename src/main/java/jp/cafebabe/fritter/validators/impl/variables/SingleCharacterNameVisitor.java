package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import org.eclipse.jdt.core.dom.*;

class SingleCharacterNameVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "%s: do not use only single character in variable name";

    private boolean checkTargetFlag = false;

    public SingleCharacterNameVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        checkTargetFlag = true;
        return super.visit(node);
    }

    @Override
    public void endVisit(FieldDeclaration node) {
        checkTargetFlag = false;
    }

    @Override
    public boolean visit(VariableDeclarationFragment node){
        String name = node.getName().toString();
        checkVariableName(node, name);
        return super.visit(node);
    }

    private void checkVariableName(VariableDeclarationFragment node, String name) {
        if(checkTargetFlag && name.length() == 1)
            add(node, Message.format(FORMATTER, name));
    }

    @Override
    public boolean visit(VariableDeclarationStatement node){
        checkTargetFlag = true;
        return super.visit(node);
    }

    @Override
    public void endVisit(VariableDeclarationStatement node){
        checkTargetFlag = false;
    }

    @Override
    public boolean visit(VariableDeclarationExpression node){
        checkTargetFlag = false;
        return super.visit(node);
    }
}
