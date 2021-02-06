package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

class ViolationCounter implements ViolationsVisitor<Long> {
    private long count = 0;

    @Override
    public void visitDataSource(DataSource source) {
        // do nothing.
    }

    @Override
    public void visitViolation(Violation violation) {
        count++;
    }

    @Override
    public void visitValidateException(ValidateException e) {
        // do nothing.
    }

    @Override
    public Long visitEnd() {
        return count;
    }
}
