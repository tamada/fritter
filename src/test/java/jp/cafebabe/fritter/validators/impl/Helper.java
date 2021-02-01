package jp.cafebabe.fritter.validators.impl;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.DefaultDataSource;
import jp.cafebabe.fritter.entities.sources.SourceFilePool;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitorAdapter;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static DataSource dataSource(Path path) {
        return new DefaultDataSource(new SourceFilePool(path), path);
    }

    public static ViolationsVisitor<List<ValidateException>> exceptionCollector() {
        return new ViolationsVisitorAdapter<List<ValidateException>>() {
            private List<ValidateException> list = new ArrayList<>();

            @Override
            public void visitValidateException(ValidateException e) {
                list.add(e);
            }

            @Override
            public List<ValidateException> visitEnd() {
                return list;
            }
        };
    }

    public static ViolationsVisitor<List<Violation>> violationCollector() {
        return new ViolationsVisitorAdapter<List<Violation>>() {
            private List<Violation> list = new ArrayList<>();

            @Override
            public void visitViolation(Violation violation) {
                list.add(violation);
            }

            @Override
            public List<Violation> visitEnd() {
                return list;
            }
        };
    }
}
