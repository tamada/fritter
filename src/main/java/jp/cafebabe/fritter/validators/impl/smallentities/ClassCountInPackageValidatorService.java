package jp.cafebabe.fritter.validators.impl.smallentities;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class ClassCountInPackageValidatorService extends AbstractValidatorService {
    public ClassCountInPackageValidatorService() {
        super(CheckerType.CLASSES_IN_PACKAGE);
    }

    @Override
    public Validator build(Parameter param) {
        return new ClassCountInPackageValidator(this, param);
    }
}
