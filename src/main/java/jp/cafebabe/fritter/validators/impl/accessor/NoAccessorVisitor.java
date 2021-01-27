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
            checkViolation(getMethodName(node), location(node));
    }

    private void checkViolation(String methodName, Location lineNumbers){
         checkViolation("get[A-Z][a-zA-Z]*$", "%s: no getter method", methodName, lineNumbers);
         checkViolation("set[A-Z][a-zA-Z]*$", "%s: no setter method", methodName, lineNumbers);
     }

    private void checkViolation(String pattern, String formatter, String methodName, Location lineNumbers) {
        if (methodName.matches(pattern))
            add(new Violation(lineNumbers, name(),
                    Message.format(formatter, methodName)));
    }
}