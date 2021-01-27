package jp.cafebabe.fritter.validators.impl.loc;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Message;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.AbstractValidator;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

import java.util.stream.Stream;

public class LinesOfFileValidator extends AbstractValidator {
    private static final String formatter = "lines of file more than %s (%s lines)";

    public LinesOfFileValidator(ValidatorService service, Parameter param) {
        super(service, param);
    }

    @Override
    public Violations validate(DataSource source) {
        return toViolations(source, countLines(source, new LineCalculator()));
    }

    private Violations toViolations(DataSource source, Either<Throwable, LinesOfCode> either) {
        return either.map(loc -> createViolationsIfNeeded(source, loc))
                .getOrElseGet(e -> new Violations(source, (ValidateException)e));
    }

    private Violations createViolationsIfNeeded(DataSource source, LinesOfCode loc) {
        if(parameter().greaterThan(loc))
            return new Violations(source);
        return new Violations(source, createViolation(loc));
    }

    private Stream<Violation> createViolation(LinesOfCode loc) {
        return Stream.of(new Violation(Location.of(1), name(),
                Message.format(formatter, parameter(), loc)));
    }

    private Either<Throwable, LinesOfCode> countLines(DataSource source, LineCalculator calculator) {
        return Try.withResources(() -> source.lines())
                .of(stream -> calculator.count(stream))
                .toEither();
    }
}
