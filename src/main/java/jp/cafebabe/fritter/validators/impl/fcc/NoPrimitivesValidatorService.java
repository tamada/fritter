package jp.cafebabe.fritter.validators.impl.fcc;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoPrimitivesValidatorService extends AbstractValidatorService {
    public NoPrimitivesValidatorService() {
        super(CheckerType.PRIMITIVE_WRAPPING);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoPrimitivesVisitor(validator));
    }
}
