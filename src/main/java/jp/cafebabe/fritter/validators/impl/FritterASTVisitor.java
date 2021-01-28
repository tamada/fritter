package jp.cafebabe.fritter.validators.impl;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.visitors.LocationVisitor;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FritterASTVisitor extends ASTVisitor {
    private Validator validator;
    private LineCalculator calculator;
    private List<Violation> list = new ArrayList<>();

    public FritterASTVisitor(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean visit(CompilationUnit unit) {
        calculator = new LineCalculator(unit);
        return super.visit(unit);
    }

    public Parameter parameter() {
        return validator.parameter();
    }

    protected void clearViolations() {
        list.clear();
    }

    protected void add(ASTNode node, Message message) {
        this.add(new Violation(location(node), validator.name(), message));
    }

    protected void add(Location location, Message message) {
        this.add(new Violation(location, validator.name(), message));
    }

    private void add(Violation violation) {
        list.add(violation);
    }

    public Location location(ASTNode node) {
        return Location.of(calculator.lineNumber(node));
    }

    public Location locations(Stream<? extends ASTNode> nodes) {
        int[] lines = nodes.mapToInt(calculator::lineNumber)
                .toArray();
        return Location.of(lines);
    }

    public int lineCount(ASTNode node) {
        return calculator.lineCount(node);
    }

    public Stream<Violation> violations() {
        return list.stream();
    }
}
