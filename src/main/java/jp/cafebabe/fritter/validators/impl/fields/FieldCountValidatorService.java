package jp.cafebabe.fritter.validators.impl.fields;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class FieldCountValidatorService extends AbstractValidatorService {
    public FieldCountValidatorService() {
        super("field_count");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new FieldCountVisitor(validator));
    }
}
