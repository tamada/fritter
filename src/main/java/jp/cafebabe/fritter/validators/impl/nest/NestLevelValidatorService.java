package jp.cafebabe.fritter.validators.impl.nest;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NestLevelValidatorService extends AbstractValidatorService {
    public NestLevelValidatorService() {
        super("indent_level");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NestLevelVisitor(validator));
    }
}
