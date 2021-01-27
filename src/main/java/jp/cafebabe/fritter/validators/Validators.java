package jp.cafebabe.fritter.validators;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validators implements Validator {
    private List<Validator> validators = new ArrayList<>();

    Validators(Stream<Validator> stream) {
        stream.forEach(validators::add);
    }

    @Override
    public CheckerType name() {
        return CheckerType.of("multiple");
    }

    public Stream<Validator> validators() {
        return validators.stream();
    }

    public Stream<CheckerType> names() {
        return validators()
                .map(validator -> validator.name());
    }

    @Override
    public Parameter parameter() {
        return Parameter.EMPTY;
    }

    @Override
    public Violations validate(DataSource source) {
        return validators().map(validator -> validateImpl(validator, source))
                .collect(Collectors.reducing(new Violations(source),
                        (before, after) -> before.merge(after)));
    }

    private Violations validateImpl(Validator validator, DataSource source) {
        return validator.validate(source);
    }
}
