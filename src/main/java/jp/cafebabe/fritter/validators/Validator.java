package jp.cafebabe.fritter.validators;

import io.vavr.control.Try;
import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.sources.DataSource;

public interface Validator {
    CheckerType name();

    Parameter parameter();

    default Violations validate(DataSource source) {
        return validate(source, new Violations(source));
    }

    Violations validate(DataSource source, Violations violations);
}
