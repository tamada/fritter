package jp.cafebabe.fritter.validators.impl.fields;

import static jp.cafebabe.fritter.validators.impl.fields.FieldCollectingVisitor.NOT_STATIC_FIELDS;

import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.Validator;
import org.eclipse.jdt.core.dom.TypeDeclaration;

class FieldCountVisitor extends FieldCollectingVisitor {
    private static final String FORMATTER = "field count is %s, more than %s";

    public FieldCountVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void endVisit(TypeDeclaration node) {
        Value fieldCount = Value.of(countFieldCount(NOT_STATIC_FIELDS));
        if(parameter().lessThan(fieldCount))
            add(fieldLocations(), Message.format(FORMATTER, fieldCount, parameter()));
    }
}
