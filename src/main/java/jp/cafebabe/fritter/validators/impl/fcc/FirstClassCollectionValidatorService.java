package jp.cafebabe.fritter.validators.impl.fcc;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class FirstClassCollectionValidatorService extends AbstractValidatorService {
    public FirstClassCollectionValidatorService() {
        super(CheckerType.FIRST_CLASS_COLLECTION);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new FirstClassCollectionVisitor(validator));
    }
}
