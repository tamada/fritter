package jp.cafebabe.fritter.validators.impl.fcc;

import static jp.cafebabe.fritter.validators.impl.fields.FieldCollectingVisitor.NOT_STATIC_FIELDS;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.fields.FieldCollectingVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

class FirstClassCollectionVisitor extends FieldCollectingVisitor {
    private static final Message MESSAGE = Message.format("not first class collection");

    public FirstClassCollectionVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void endVisit(TypeDeclaration node) {
        checkViolation();
    }

    private void checkViolation() {
        if(isViolated())
            add(fieldLocations(), MESSAGE);
    }

    private boolean isViolated() {
        long notStaticFieldCount = countFieldCount(NOT_STATIC_FIELDS);
        long collectionFieldCount = countFieldCount(NOT_STATIC_FIELDS.and(this::isCollection));
        return notStaticFieldCount > 1 && collectionFieldCount > 0;
    }

    private boolean isCollection(FieldDeclaration node) {
        Type type = node.getType();
        return type.isArrayType()
                || CollectionTypeChecker.isCollection(type);
    }
}
