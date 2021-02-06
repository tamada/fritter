package jp.cafebabe.fritter.validators.impl.elsestatement;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoElseValidatorService extends AbstractValidatorService {
    public NoElseValidatorService() {
        super(CheckerType.NO_ELSE);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoElseVisitor(validator));
    }
}
