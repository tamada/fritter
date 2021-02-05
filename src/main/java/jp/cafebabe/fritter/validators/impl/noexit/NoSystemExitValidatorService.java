package jp.cafebabe.fritter.validators.impl.noexit;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoSystemExitValidatorService extends AbstractValidatorService {
    public NoSystemExitValidatorService() {
        super(CheckerType.NO_SYSTEM_EXIT);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoSystemExitVisitor(validator));
    }
}
