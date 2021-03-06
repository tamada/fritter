package jp.cafebabe.fritter.validators.impl.variables;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class SingleCharacterNameValidatorService extends AbstractValidatorService {
    public SingleCharacterNameValidatorService() {
        super(CheckerType.SINGLE_CHARACTER_NAME);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new SingleCharacterNameVisitor(validator));
    }
}
