package jp.cafebabe.fritter.validators.impl.fields;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;

class FieldCountVisitor extends FieldCollectingVisitor {
    private static final String FORMATTER = "field count is %s, more than %s";

    public FieldCountVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration node, Violations violations) {
        super.visit(node, violations);
        checkFieldCount(violations);
    }

    private void checkFieldCount(Violations violations) {
        Value fieldCount = Value.of(countFieldCount(NOT_STATIC_FIELDS));
        if(parameter().lessThan(fieldCount))
            violations.add(createViolation(fieldLocations(NOT_STATIC_FIELDS),
                    Message.format(FORMATTER, fieldCount, parameter())));
    }
}
