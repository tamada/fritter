package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;

import java.util.ArrayList;
import java.util.List;
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
    public Violations validate(DataSource source, Violations violations) {
        validators()
                .forEach(validator -> validator.validate(source, violations));
        return violations;
    }
}
