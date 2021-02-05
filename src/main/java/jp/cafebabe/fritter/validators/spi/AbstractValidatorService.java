package jp.cafebabe.fritter.validators.spi;

import jp.cafebabe.fritter.config.CheckerType;

public abstract class AbstractValidatorService implements ValidatorService {
    private CheckerType checkerType;

    protected AbstractValidatorService(CheckerType type) {
        checkerType = type;
    }

    @Override
    public final CheckerType name(){
        return checkerType;
    }
}
