package jp.cafebabe.fritter.validators.impl.fcc;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.Type;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.fields.FieldCollectingVisitor;

class FirstClassCollectionVisitor extends FieldCollectingVisitor {
    private static final Message MESSAGE = Message.format("not first class collection");

    public FirstClassCollectionVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration node, Violations violations) {
        super.visit(node, violations);
        checkViolation(violations);
    }

    private void checkViolation(Violations violations) {
        if(isViolated())
            violations.add(createViolation(fieldLocations(NOT_STATIC_FIELDS), MESSAGE));
    }

    private boolean isViolated() {
        long notStaticFieldCount = countFieldCount(NOT_STATIC_FIELDS);
        long collectionFieldCount = countFieldCount(NOT_STATIC_FIELDS.and(TypeChecker::isCollection));
        return notStaticFieldCount > 1 && collectionFieldCount > 0;
    }

}
