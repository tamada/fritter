package jp.cafebabe.fritter.validators.impl.smallentities;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class LinesOfMethodValidatorService extends AbstractValidatorService {
    public LinesOfMethodValidatorService() {
        super(CheckerType.LINES_OF_METHOD);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                validator -> new LinesOfMethodVisitor(validator));
    }
}
