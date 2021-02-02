package jp.cafebabe.fritter.validators.impl.nort;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.DeclarationsUtils;

class NoReturnCodeInPrintfVisitor extends FritterASTVisitor {
    private static enum State {
        InPrintf,
        Other
    }
    private State state = State.Other;

    public NoReturnCodeInPrintfVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(MethodCallExpr node, Violations violations) {
        if(isPrintfCall(node))
            state = State.InPrintf;
        super.visit(node, violations);
        state = State.Other;
    }

    @Override
    public void visit(StringLiteralExpr literal, Violations violations) {
        if(state == State.InPrintf && isViolate(literal.getValue()))
            violations.add(createViolation(literal, Message.format("no \'\\n\', use \'%%n\' in printf")));
        super.visit(literal, violations);
    }

    private boolean isViolate(String str) {
        return str.contains("\\n");
    }

    private boolean isPrintfCall(MethodCallExpr node) {
        return DeclarationsUtils.isName(node.getName(), "printf");
    }
}