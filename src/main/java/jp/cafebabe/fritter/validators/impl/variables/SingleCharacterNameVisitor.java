package jp.cafebabe.fritter.validators.impl.variables;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

class SingleCharacterNameVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "%s: do not use only single character in variable name";

    private boolean checkTargetFlag = false;

    public SingleCharacterNameVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(FieldDeclaration node, Violations violations) {
        checkTargetFlag = true;
        super.visit(node, violations);
        checkTargetFlag = false;
    }

    @Override
    public void visit(VariableDeclarator node, Violations violations){
        SimpleName name = node.getName();
        checkVariableName(node, name.getIdentifier(), violations);
        super.visit(node, violations);
    }

    private void checkVariableName(VariableDeclarator node, String name, Violations violations) {
        if(checkTargetFlag && name.length() == 1)
            violations.add(createViolation(node, Message.format(FORMATTER, name)));
    }

    @Override
    public void visit(VariableDeclarationExpr node, Violations violations){
        checkTargetFlag = false;
        super.visit(node, violations);
        checkTargetFlag = false;
    }
}
