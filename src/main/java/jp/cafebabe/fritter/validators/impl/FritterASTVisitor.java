package jp.cafebabe.fritter.validators.impl;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import io.vavr.control.Option;
import jp.cafebabe.fritter.annotation.Ignore;
import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class FritterASTVisitor extends VoidVisitorAdapter<Violations> {
    private Validator validator;

    public FritterASTVisitor(Validator validator) {
        this.validator = validator;
    }

    protected Parameter parameter() {
        return validator.parameter();
    }

    protected Violation createViolation(Node node, Message message) {
        return new Violation(LineCalculatorUtils.location(node), validator.name(), message);
    }

    protected Violation createViolation(Location location, Message message) {
        return new Violation(location, validator.name(), message);
    }

    private <T extends NodeWithAnnotations<? extends Node>> Optional<AnnotationExpr> ifIgnore(T node, Violations violations) {
        Optional<AnnotationExpr> optional = node.getAnnotationByClass(Ignore.class);
        return optional.map(expr ->
                contains(expr.asSingleMemberAnnotationExpr(), validator.name()));
    }

    private AnnotationExpr contains(SingleMemberAnnotationExpr expr, CheckerType type) {
        String value = convertToStringRepresentation(expr.getMemberValue());
        if(Objects.equals(value, "") || value.contains(type.name()))
            return expr;
        return null;
    }

    private String convertToStringRepresentation(Expression expr) {
        return expr.toString()
                .toUpperCase();
    }

    public <T extends NodeWithAnnotations<? extends Node>> void performIfTarget(T node, Violations violations,
                                                                                BiConsumer<T, Violations> consumer) {
        ifIgnore(node, violations)
                .ifPresentOrElse(expr -> {}, () -> consumer.accept(node, violations));
    }
}
