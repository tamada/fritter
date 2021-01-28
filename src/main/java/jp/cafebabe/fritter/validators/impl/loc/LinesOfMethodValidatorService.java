package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class LinesOfMethodValidatorService extends AbstractValidatorService {
    public LinesOfMethodValidatorService() {
        super("lines_of_class");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                validator -> new LinesOfMethodVisitor(validator));
    }
}
