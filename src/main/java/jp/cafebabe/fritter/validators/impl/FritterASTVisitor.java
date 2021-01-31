package jp.cafebabe.fritter.validators.impl;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.util.stream.Stream;

public class FritterASTVisitor extends VoidVisitorAdapter<Violations> {
    private Validator validator;

    public FritterASTVisitor(Validator validator) {
        this.validator = validator;
    }

    public Parameter parameter() {
        return validator.parameter();
    }

    protected Violation createViolation(Node node, Message message) {
        return new Violation(location(node), validator.name(), message);
    }

    protected Violation createViolation(Location location, Message message) {
        return new Violation(location, validator.name(), message);
    }

    public Location location(Node node) {
        return Location.of(
                LineCalculator.lineNumber(node));
    }

    public Location locations(Stream<? extends Node> nodes) {
        int[] lines = nodes.mapToInt(LineCalculator::lineNumber)
                .toArray();
        return Location.of(lines);
    }
}
