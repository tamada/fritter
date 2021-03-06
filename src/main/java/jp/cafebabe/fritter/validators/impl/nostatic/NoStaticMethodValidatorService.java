package jp.cafebabe.fritter.validators.impl.nostatic;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoStaticMethodValidatorService extends AbstractValidatorService {
    public NoStaticMethodValidatorService() {
        super(CheckerType.NO_STATIC_METHOD);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoStaticMethodVisitor(validator));
    }
}
