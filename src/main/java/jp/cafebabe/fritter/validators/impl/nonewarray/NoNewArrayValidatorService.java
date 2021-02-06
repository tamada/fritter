package jp.cafebabe.fritter.validators.impl.nonewarray;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoNewArrayValidatorService extends AbstractValidatorService {
    public NoNewArrayValidatorService() {
        super(CheckerType.NO_NEW_ARRAY);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoNewArrayVisitor(validator));
    }
}
