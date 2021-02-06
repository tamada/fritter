package jp.cafebabe.fritter.validators.impl.smallentities;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;

class LinesOfClassVisitor extends LinesOfCodeVisitor {
    public LinesOfClassVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> checkViolation(n, "class", v));
        super.visit(node, violations);
    }

    @Override
    public void visit(EnumDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> checkViolation(n, "enum", v));
        super.visit(node, violations);
    }

    @Override
    public void visit(AnnotationDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> checkViolation(n, "annotation", v));
        super.visit(node, violations);
    }
}