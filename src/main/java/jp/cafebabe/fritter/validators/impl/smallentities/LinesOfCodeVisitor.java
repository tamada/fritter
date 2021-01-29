package jp.cafebabe.fritter.validators.impl.smallentities;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import org.eclipse.jdt.core.dom.ASTNode;

class LinesOfCodeVisitor extends FritterASTVisitor {
    public LinesOfCodeVisitor(Validator validator) {
        super(validator);
    }

    protected void checkViolation(ASTNode node, String type ) {
        LinesOfCode loc = LinesOfCode.of(lineCount(node));
        if(loc.greaterThan(parameter()))
            add(node, Message.format("lines of %s is %s, more than %s", type, loc, parameter()));
    }
}