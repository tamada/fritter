package jp.cafebabe.fritter.validators.impl.smallentities;

import com.github.javaparser.ast.Node;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.LineCalculatorUtils;

class LinesOfCodeVisitor extends FritterASTVisitor {
    public LinesOfCodeVisitor(Validator validator) {
        super(validator);
    }

    protected void checkViolation(Node node, String type, Violations v) {
        LinesOfCode loc = LinesOfCode.of(LineCalculatorUtils.lineCount(node));
        if(loc.greaterThan(parameter()))
            add(node, Message.format("lines of %s is %s, more than %s", type, loc, parameter()), v);
    }

    private void add(Node node, Message message, Violations v) {
        v.add(createViolation(node, message));
    }
}