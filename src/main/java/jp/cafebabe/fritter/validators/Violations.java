package jp.cafebabe.fritter.validators;

import io.vavr.control.Either;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.visitors.ViolationsVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Violations {
    private List<Either<ValidateException, Violation>> list;
    private DataSource source;

    public Violations(DataSource source, Stream<Violation> stream) {
        this(source);
        stream.forEach(this::add);
    }

    public Violations(DataSource source, ValidateException exp) {
        this(source);
        add(exp);
    }

    public Violations(DataSource source) {
        list = new ArrayList<>();
        this.source = source;
    }

    public <S> S accept(ViolationsVisitor<S> visitor) {
        visitor.visitDataSource(source);
        list.stream()
                .forEach(either -> visitEither(visitor, either));
        return visitor.visitEnd();
    }

    private void visitEither(ViolationsVisitor visitor, Either<ValidateException, Violation> either) {
        either.peek(violation -> visitor.visitViolation(violation))
                .orElseRun(exception -> visitor.visitValidateException(exception));
    }

    public boolean hasExceptions() {
        return count(either -> either.isLeft()) > 0;
    }

    public boolean hasViolations() {
        return count(either -> either.isRight()) > 0;
    }

    private long count(Predicate<Either<ValidateException, Violation>> predicate) {
        return list.stream()
                .filter(predicate)
                .count();
    }

    public DataSource source() {
        return source;
    }

    public void add(Violation violation) {
        list.add(Either.right(violation));
    }

    public void add(ValidateException exception) {
        list.add(Either.left(exception));
    }
}
