package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

public abstract class AbstractValidator implements Validator {
    private ValidatorService service;

    public AbstractValidator(ValidatorService service) {
        this.service = service;
    }

    public CheckerType name() {
        return service().name();
    }

    public ValidatorService service() {
        return service;
    }
}
