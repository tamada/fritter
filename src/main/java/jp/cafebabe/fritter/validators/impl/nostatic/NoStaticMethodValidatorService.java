package jp.cafebabe.fritter.validators.impl.nostatic;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoStaticMethodValidatorService extends AbstractValidatorService {
    public NoStaticMethodValidatorService() {
        super("no_static_method");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoStaticMethodVisitor(validator));
    }
}
