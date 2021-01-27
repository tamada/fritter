package jp.cafebabe.fritter.validators.impl.accessor;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.loc.LinesOfFileValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoAccessorValidatorService extends AbstractValidatorService {
    public NoAccessorValidatorService() {
        super("no_accessor");
    }

    @Override
    public Validator build(Parameter param) {
        return new NoAccessorValidator(this, param);
    }
}
