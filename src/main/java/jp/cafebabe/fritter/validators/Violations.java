package jp.cafebabe.fritter.validators;

import io.vavr.control.Either;
import jp.cafebabe.fritter.entities.sources.DataSource;

import java.util.ArrayList;
import java.util.List;
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

    public boolean hasExceptions() {
        return exceptions().count() > 0;
    }

    public boolean hasViolations() {
        return stream().count() > 0;
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

    public Stream<ValidateException> exceptions() {
        return list.stream()
                .filter(either -> either.isLeft())
                .map(either -> either.getLeft());
    }

    public Stream<Violation> stream() {
        return list.stream()
                .filter(either -> either.isRight())
                .map(either -> either.get());
    }

    public Violations merge(Violations violations) {
        if(source.isSame(violations.source))
            return mergeImpl(violations);
        throw new IllegalArgumentException("different DataSource: " + source.path());
    }

    private Violations mergeImpl(Violations other) {
        list.addAll(other.list);
        return this;
    }
}
