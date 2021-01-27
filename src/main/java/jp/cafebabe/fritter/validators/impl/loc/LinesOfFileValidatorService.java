package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class LinesOfFileValidatorService extends AbstractValidatorService {
    public LinesOfFileValidatorService() {
        super("lines_of_file");
    }

    @Override
    public Validator build(Parameter param) {
        return new LinesOfFileValidator(this, param);
    }
}
