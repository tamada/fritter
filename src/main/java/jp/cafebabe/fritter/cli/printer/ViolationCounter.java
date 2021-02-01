package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

public class ViolationCounter implements ViolationsVisitor<Long> {
    long count = 0;

    @Override
    public void visitDataSource(DataSource source) {
    }

    @Override
    public void visitViolation(Violation violation) {
        count++;
    }

    @Override
    public void visitValidateException(ValidateException e) {
    }

    @Override
    public Long visitEnd() {
        return count;
    }
}
