package jp.cafebabe.fritter.validators.impl.fcc;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.DeclarationsUtils;
import jp.cafebabe.fritter.validators.impl.fields.FieldCollectingVisitor;

import java.util.function.Predicate;

class NoPrimitivesVisitor extends FieldCollectingVisitor {
    private static final Message MESSAGE = Message.format("do not use raw primitive, wrap it");

    private static final Predicate<FieldDeclaration> PREDICATE = (node) -> !DeclarationsUtils.isStatic(node);
    private static final Predicate<FieldDeclaration> PRIMITIVES = PREDICATE.and(TypeChecker::isPrimitive);

    public NoPrimitivesVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration node, Violations violations) {
        super.visit(node, violations);
        performIfTarget(node, violations, (n, v) -> checkViolations(v));
    }

    private void checkViolations(Violations violations) {
        if (isViolated())
            violations.add(createViolation(fieldLocations(), MESSAGE));
    }

    private boolean isViolated() {
        long notStaticFieldCount = countFieldCount(PREDICATE);
        long primitiveField = countFieldCount(PRIMITIVES);
        return notStaticFieldCount > 1 && primitiveField > 0;
    }
}
