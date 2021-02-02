package jp.cafebabe.fritter.validators.spi;

import jp.cafebabe.fritter.config.CheckerType;

public abstract class AbstractValidatorService implements ValidatorService {
    private CheckerType name;

    protected AbstractValidatorService(String name) {
        this.name = CheckerType.of(name);
    }

    @Override
    public final CheckerType name(){
        return name;
    }
}
