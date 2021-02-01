package jp.cafebabe.fritter.validators.impl.accessor;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoAccessorValidatorService extends AbstractValidatorService {
    public NoAccessorValidatorService() {
        super("no_accessor");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                validator -> new NoAccessorVisitor(validator));
    }
}
