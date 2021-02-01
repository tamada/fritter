package jp.cafebabe.fritter.validators.impl.smallentities;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class LinesOfClassValidatorService extends AbstractValidatorService {
    public LinesOfClassValidatorService() {
        super("lines_of_class");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                validator -> new LinesOfClassVisitor(validator));
    }
}
