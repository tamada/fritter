package jp.cafebabe.fritter.validators.impl.fields;

import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FieldCollectingVisitor extends FritterASTVisitor {
    private List<FieldDeclaration> fields = new ArrayList<>();

    public static final Predicate<FieldDeclaration> NOT_STATIC_FIELDS = node -> !Utils.isStatic(node);

    public FieldCollectingVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        fields.clear();
        return super.visit(node);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        fields.add(node);
        return super.visit(node);
    }

    public Location fieldLocations() {
        return locations(fields.stream());
    }

    public long countFieldCount(Predicate<FieldDeclaration> predicate) {
        return fields.stream()
                .filter(predicate)
                .count();
    }
}
