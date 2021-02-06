package jp.cafebabe.fritter.validators.impl.nostatic;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;

class NoStaticMethodVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "%s: no static method except main method";

    private AllStaticState allStatic;
    private ViolationHolder holder = new ViolationHolder();

    public NoStaticMethodVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration node, Violations violations) {
        allStatic = new AllStaticState(node.getName());
        performIfTarget(node, violations, (n, v) -> checkStart(n, v));
    }

    private void checkStart(ClassOrInterfaceDeclaration node, Violations violations) {
        super.visit(node, violations);
        holder.addIfTo(allStatic, violations);
    }

    @Override
    public void visit(MethodDeclaration node, Violations violations) {
        performIfTarget(node, violations, (n, v) -> checkViolation(n, v));
    }

    private void checkViolation(MethodDeclaration node, Violations violations) {
        if(allStatic.checkAllStatic(node))
            holder.add(createViolation(node, Message.format(FORMATTER, node.getName())));
        super.visit(node, violations);
    }
}
