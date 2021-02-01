package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class VariableCountValidatorService extends AbstractValidatorService {
    public VariableCountValidatorService() {
        super("variable_count");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new VariableCountVisitor(validator));
    }
}
