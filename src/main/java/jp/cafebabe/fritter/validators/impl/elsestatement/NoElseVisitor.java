package jp.cafebabe.fritter.validators.impl.elsestatement;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class NoElseVisitor extends FritterASTVisitor {
    private static final Message MESSAGE = Message.format("no else statement");
    public NoElseVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(IfStatement node) {
        checkViolation(node, node.getElseStatement());
        return super.visit(node);
    }

    private void checkViolation(IfStatement node, Statement statement) {
        if(isExist(Optional.ofNullable(statement)))
            add(statement, MESSAGE);
    }

    private boolean isExist(Optional<Statement> elseStatement) {
        return elseStatement
                .map(statement -> statement.getNodeType() != ASTNode.IF_STATEMENT)
                .orElse(false);
    }
}
