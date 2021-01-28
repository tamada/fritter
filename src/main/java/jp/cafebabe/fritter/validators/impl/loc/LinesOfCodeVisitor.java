package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class LinesOfCodeVisitor extends FritterASTVisitor {
    public LinesOfCodeVisitor(Validator validator) {
        super(validator);
    }

    protected void checkViolation(ASTNode node, String type ) {
        LinesOfCode loc = LinesOfCode.of(lineCount(node));
        if(loc.greaterThan(parameter()))
            add(node, Message.format("lines of %s more than (%s lines)", type, parameter(), loc));
    }
}