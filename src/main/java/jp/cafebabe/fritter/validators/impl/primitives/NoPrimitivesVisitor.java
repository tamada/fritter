package jp.cafebabe.fritter.validators.impl.primitives;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.Utils;
import jp.cafebabe.fritter.validators.impl.fields.FieldCollectingVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.function.Predicate;

class NoPrimitivesVisitor extends FieldCollectingVisitor {
    private static final Message MESSAGE = Message.format("do not use raw primitive, wrap it");

    private static final Predicate<FieldDeclaration> PREDICATE = (node) -> !Utils.isStatic(node);
    private static final Predicate<FieldDeclaration> PRIMITIVES = PREDICATE.and(PrimitiveChecker::check);

    public NoPrimitivesVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void endVisit(TypeDeclaration node) {
        if (isViolated())
            add(fieldLocations(), MESSAGE);
    }

    private boolean isViolated() {
        long notStaticFieldCount = countFieldCount(PREDICATE);
        long primitiveField = countFieldCount(PRIMITIVES);
        return notStaticFieldCount > 1 && primitiveField > 0;
    }
}
