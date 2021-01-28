package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class LinesOfClassVisitor extends LinesOfCodeVisitor {
    public LinesOfClassVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(TypeDeclaration type) {
        checkViolation(type, "class");
        return super.visit(type);
    }

    @Override
    public boolean visit(EnumDeclaration declaration) {
        checkViolation(declaration, "enum");
        return super.visit(declaration);
    }

    @Override
    public boolean visit(AnnotationTypeDeclaration declaration) {
        checkViolation(declaration, "annotation");
        return super.visit(declaration);
    }
}