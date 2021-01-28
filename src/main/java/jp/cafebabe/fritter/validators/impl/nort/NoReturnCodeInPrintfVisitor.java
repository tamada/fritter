package jp.cafebabe.fritter.validators.impl.nort;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public class NoReturnCodeInPrintfVisitor extends FritterASTVisitor {
    private static enum State {
        InPrintf,
        Other
    }
    private State state = State.Other;

    public NoReturnCodeInPrintfVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        if(isPrintfCall(node))
            state = State.InPrintf;
        return super.visit(node);
    }

    @Override
    public void endVisit(MethodInvocation node) {
        state = State.Other;
    }

    @Override
    public boolean visit(StringLiteral literal) {
        if(state == State.InPrintf)
            checkViolation(literal, literal.getEscapedValue());
        return super.visit(literal);
    }

    private void checkViolation(StringLiteral literal, String str) {
        if(str.contains("\\n"))
            add(literal, Message.format("no \'\\n\', use \'%%n\' in printf"));
    }

    private boolean isPrintfCall(MethodInvocation node) {
        return Utils.isName(node.getName(), "printf");
    }

    private String getMethodName(MethodDeclaration node) {
        SimpleName name = node.getName();
        return name.getIdentifier();
    }
}