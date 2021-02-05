package jp.cafebabe.fritter.validators.impl.fields;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.DeclarationsUtils;
import jp.cafebabe.fritter.validators.impl.LineCalculatorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FieldCollectingVisitor extends FritterASTVisitor {
    private List<FieldDeclaration> fields = new ArrayList<>();

    public static final Predicate<FieldDeclaration> NOT_STATIC_FIELDS = node -> !DeclarationsUtils.isStatic(node);

    public FieldCollectingVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration node, Violations violations) {
        fields.clear();
        performIfTarget(node, violations, (n, v) -> super.visit(n, v));
    }

    @Override
    public void visit(FieldDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> fields.add(n));
    }

    public Location fieldLocations() {
        return fieldLocations(field -> true);
    }

    public Location fieldLocations(Predicate<FieldDeclaration> predicate) {
        return LineCalculatorUtils.locations(
                fields.stream()
                        .filter(predicate));
    }

    public long countFieldCount(Predicate<FieldDeclaration> predicate) {
        return fields.stream()
                .filter(predicate)
                .count();
    }
}
