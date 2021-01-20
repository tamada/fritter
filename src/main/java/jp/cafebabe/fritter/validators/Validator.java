package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

public interface Validator {
    CheckerType name();

    Violations validate(DataSource source) throws ValidateException;
}
