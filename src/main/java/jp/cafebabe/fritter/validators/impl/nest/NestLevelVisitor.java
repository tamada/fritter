package jp.cafebabe.fritter.validators.impl.nest;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.*;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

import java.util.Optional;
import java.util.Stack;

class NestLevelVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "%s: indent level is %s, more than %s";

    private Stack<Statement> stack = new Stack<Statement>();
    private Optional<Violation> violation = Optional.empty();
    private SimpleName name;

    public NestLevelVisitor(Validator validator) {
        super(validator);
    }

    private void initialize(MethodDeclaration node) {
        stack.clear();
        violation = Optional.empty();
        this.name = node.getName();
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        initialize(node);
        super.visit(node, violations);
        violation.ifPresent(v -> violations.add(v));
    }

    @Override
    public void visit(IfStmt node, Violations violations) {
        nest(node);
        super.visit(node, violations);
        unnest(node);
    }

    @Override
    public void visit(ForStmt node, Violations violations) {
        nest(node);
        super.visit(node, violations);
        unnest(node);
    }

    @Override
    public void visit(WhileStmt node, Violations violations) {
        nest(node);
        super.visit(node, violations);
        unnest(node);
    }

    @Override
    public void visit(DoStmt node, Violations violations) {
        nest(node);
        super.visit(node, violations);
        unnest(node);
    }

    @Override
    public void visit(TryStmt node, Violations violations) {
        nest(node);
        super.visit(node, violations);
        unnest(node);
    }

    @Override
    public void visit(SwitchStmt node, Violations violations) {
        nest(node);
        super.visit(node, violations);
        unnest(node);
    }

    private void nest(Statement statement) {
        stack.push(statement);
        if(parameter().lessThan(Value.of(stack.size())))
            violation = Optional.of(create(statement));
    }

    private Violation create(Statement statement) {
        return createViolation(statement,
                Message.format(FORMATTER, name,
                        stack.size(), parameter()));
    }

    private void unnest(Statement statement) {
        Statement poppedStatement = stack.pop();
        if(poppedStatement != statement)
            throw new ValidateException("popped statement did not match, wont " + statement + ", but got " + poppedStatement);
    }
}
