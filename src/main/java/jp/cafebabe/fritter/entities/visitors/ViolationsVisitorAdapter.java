package jp.cafebabe.fritter.entities.visitors;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

public abstract class ViolationsVisitorAdapter<T> implements ViolationsVisitor<T> {
    @Override
    public void visitDataSource(DataSource source) {
        // do nothing.
    }

    @Override
    public void visitViolation(Violation violation) {
        // do nothing.
    }

    @Override
    public void visitValidateException(ValidateException e) {
        // do nothing.
    }

    @Override
    public abstract T visitEnd();
}
