package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class LocalVariableCountValidatorService extends AbstractValidatorService {
    public LocalVariableCountValidatorService() {
        super(CheckerType.VARIABLE_COUNT);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new LocalVariableCountVisitor(validator));
    }
}
