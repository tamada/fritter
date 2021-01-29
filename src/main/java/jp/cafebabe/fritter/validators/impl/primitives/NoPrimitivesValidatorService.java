package jp.cafebabe.fritter.validators.impl.primitives;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoPrimitivesValidatorService extends AbstractValidatorService {
    public NoPrimitivesValidatorService() {
        super("primitive_wrapping");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoPrimitivesVisitor(validator));
    }
}
