package jp.cafebabe.fritter.validators.impl.elsestatement;

import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

import java.util.Optional;

class NoElseVisitor extends FritterASTVisitor {
    private static final Message MESSAGE = Message.format("no else statement");

    public NoElseVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(IfStmt node, Violations violations) {
        checkViolation(node, node.getElseStmt(), violations);
        super.visit(node, violations);
    }

    private void checkViolation(IfStmt node, Optional<Statement> optional, Violations violations) {
        optional.map(statement -> createViolation(statement, MESSAGE))
                .ifPresent(violations::add);
    }
}
