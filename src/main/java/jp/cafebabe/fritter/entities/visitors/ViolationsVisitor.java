package jp.cafebabe.fritter.entities.visitors;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

public interface ViolationsVisitor<S> {
    void visitDataSource(DataSource source);

    void visitViolation(Violation violation);

    void visitValidateException(ValidateException e);

    S visitEnd();
}
