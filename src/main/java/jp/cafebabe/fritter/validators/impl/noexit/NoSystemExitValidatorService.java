package jp.cafebabe.fritter.validators.impl.noexit;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoSystemExitValidatorService extends AbstractValidatorService {
    public NoSystemExitValidatorService() {
        super("no_system_exit");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoSystemExitVisitor(validator));
    }
}
