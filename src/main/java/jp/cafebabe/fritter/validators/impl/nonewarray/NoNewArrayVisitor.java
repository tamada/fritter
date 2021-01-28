package jp.cafebabe.fritter.validators.impl.nonewarray;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

class NoNewArrayVisitor extends FritterASTVisitor {
    private static final Message MESSAGE = Message.format("do not use array, use List instead");

    public NoNewArrayVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(ArrayCreation node) {
        add(node, MESSAGE);
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayInitializer node) {
        add(node, MESSAGE);
        return super.visit(node);
    }
}
