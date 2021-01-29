package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;

public class ViolationsMerger {
    public Violations merge(Violations v1, Violations v2) {
        if(isSameSource(v1.source(), v2.source()))
            return mergeImpl(v1, v2);
        throw new IllegalArgumentException(constructMessage(v1.source(), v2.source()));
    }

    private boolean isSameSource(DataSource ds1, DataSource ds2) {
        return ds1.isSame(ds2);
    }

    private String constructMessage(DataSource ds1, DataSource ds2) {
        return String.format("different data source: %s and %s",
                ds1.path(), ds2.path());
    }

    private Violations mergeImpl(Violations v1, Violations v2) {
        Visitor visitor = new Visitor();
        v1.accept(visitor);
        return v2.accept(visitor);
    }

    private static class Visitor implements ViolationsVisitor<Violations> {
        private Violations violations;

        @Override
        public void visitDataSource(DataSource source) {
            if (violations == null)
                violations = new Violations(source);
        }

        @Override
        public void visitViolation(Violation violation) {
            violations.add(violation);
        }

        @Override
        public void visitValidateException(ValidateException e) {
            violations.add(e);
        }

        @Override
        public Violations visitEnd() {
            return violations;
        }
    }
}
