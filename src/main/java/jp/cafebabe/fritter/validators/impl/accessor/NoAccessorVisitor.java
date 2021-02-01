package jp.cafebabe.fritter.validators.impl.accessor;

import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class NoAccessorVisitor extends FritterASTVisitor {
    private AccessorChecker checker = new AccessorChecker();

    public NoAccessorVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        checkViolation(node);
        return super.visit(node);
    }

    private String getMethodName(MethodDeclaration node) {
        SimpleName name = node.getName();
        return name.getIdentifier();
    }

    private void checkViolation(MethodDeclaration node) {
        if(checker.isPublicMethod(node))
            checkViolation(node, getMethodName(node));
    }

    private void checkViolation(MethodDeclaration method, String methodName) {
         checkViolation("get[A-Z][a-zA-Z]*$", "%s: no getter method", methodName, method);
         checkViolation("set[A-Z][a-zA-Z]*$", "%s: no setter method", methodName, method);
     }

    private void checkViolation(String pattern, String formatter, String methodName, MethodDeclaration method) {
        if (methodName.matches(pattern))
            add(method, Message.format(formatter, methodName));
    }
}