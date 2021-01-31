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
    public void visit(ClassOrInterfaceDeclaration type, Violations v) {
        checkViolation(type, "class", v);
        super.visit(type, v);
    }

    @Override
    public void visit(EnumDeclaration declaration, Violations v) {
        checkViolation(declaration, "enum", v);
        super.visit(declaration, v);
    }

    @Override
    public void visit(AnnotationDeclaration declaration, Violations v) {
        checkViolation(declaration, "annotation", v);
        super.visit(declaration, v);
    }
}