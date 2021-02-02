package jp.cafebabe.fritter.validators;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.spi.ValidatorService;

public abstract class AbstractValidator implements Validator {
    private ValidatorService service;
    private Parameter parameter;

    public AbstractValidator(ValidatorService service, Parameter parameter) {
        this.service = service;
        this.parameter = parameter;
    }

    public Parameter parameter() {
        return parameter;
    }

    public CheckerType name() {
        return service().name();
    }

    public ValidatorService service() {
        return service;
    }
}
