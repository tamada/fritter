package jp.cafebabe.fritter.validators.impl.fields;

import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Value;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.FritterASTVisitor;
import jp.cafebabe.fritter.validators.impl.Utils;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

class FieldCountVisitor extends FritterASTVisitor {
    private static final String FORMATTER = "field count is %s, more than %s";
    private List<FieldDeclaration> list = new ArrayList<>();

    public FieldCountVisitor(Validator validator) {
        super(validator);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        list.clear();
        return super.visit(node);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        if(!Utils.isStatic(node))
            list.add(node);
        return super.visit(node);
    }

    @Override
    public void endVisit(TypeDeclaration node) {
        if(parameter().lessThan(Value.of(list.size())))
            add(buildLocation(), Message.format(FORMATTER, list.size(), parameter()));
    }

    private Location buildLocation() {
        return locations(list.stream());
    }
}
