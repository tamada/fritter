package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class SingleCharacterNameValidator extends AbstractValidatorService {
    public SingleCharacterNameValidator() {
        super("single_character_name");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new SingleCharacterNameVisitor(validator));
    }
}
