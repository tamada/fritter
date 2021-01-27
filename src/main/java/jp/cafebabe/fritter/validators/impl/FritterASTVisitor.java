package jp.cafebabe.fritter.validators.impl;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.visitors.LocationVisitor;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
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
        super();
        this.validator = validator;
    }

    @Override
    public boolean visit(CompilationUnit unit) {
        calculator = new LineCalculator(unit);
        return super.visit(unit);
    }

    protected void add(Violation violation) {
        list.add(violation);
    }

    public Location location(ASTNode node) {
        return Location.of(calculator.lineNumber(node));
    }

    public int lineCount(ASTNode node) {
        return calculator.lineCount(node);
    }

    public CheckerType name() {
        return validator.name();
    }

    public Stream<Violation> violations() {
        return list.stream();
    }
}
