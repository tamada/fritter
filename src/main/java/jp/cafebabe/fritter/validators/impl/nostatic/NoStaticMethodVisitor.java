package jp.cafebabe.fritter.validators.impl.nostatic;

import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class NoStaticMethodVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "%s: no static method except main method";
    private List<Violation> list = new ArrayList<>();
    private boolean allStatic = true;

    public NoStaticMethodVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        if(!Utils.isMainMethod(node))
            checkViolation(node);
        return super.visit(node);
    }

    @Override
    public void endVisit(CompilationUnit unit) {
        if(allStatic)
            clearViolations();
    }

    private void checkViolation(MethodDeclaration node) {
        if (node.isConstructor())
            return;
        checkViolationImpl(node);
    }

    private void checkViolationImpl(MethodDeclaration node) {
        allStatic = allStatic && Utils.isStatic(node);
        if(Utils.isStatic(node))
            add(node, Message.format(FORMATTER, node.getName()));
    }
}
