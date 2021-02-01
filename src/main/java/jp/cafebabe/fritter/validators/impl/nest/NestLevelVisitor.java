package jp.cafebabe.fritter.validators.impl.nest;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.util.Objects;
import java.util.Optional;

class NestLevelVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "indent level is %s, more than %s";

    public NestLevelVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(MethodDeclaration node) {
        IndentLevel level = computeIndentLevel(node);
        if(parameter().lessThan(level))
            add(node, Message.format(FORMATTER, level, parameter()));
    }

    private IndentLevel computeIndentLevel(MethodDeclaration node) {
        return computeMaxIndentLevel(node)
                .orElseGet(() -> new IndentLevel(0));
    }

    private Optional<IndentLevel> computeMaxIndentLevel(MethodDeclaration node) {
        IndentManipulator manipulator = new IndentManipulator(node);
        return new IndentLevelStream().stream(manipulator)
                .max(IndentLevel::compareTo);
    }
}
