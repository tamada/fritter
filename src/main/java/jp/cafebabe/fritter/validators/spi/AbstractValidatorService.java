package jp.cafebabe.fritter.validators.spi;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;

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
