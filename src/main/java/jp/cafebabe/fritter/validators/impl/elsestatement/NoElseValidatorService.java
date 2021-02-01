package jp.cafebabe.fritter.validators.impl.elsestatement;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoElseValidatorService extends AbstractValidatorService {
    public NoElseValidatorService() {
        super("no_else");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoElseVisitor(validator));
    }
}
