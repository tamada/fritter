package jp.cafebabe.fritter.validators.impl.nort;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.impl.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoReturnCodeInPrintfValidatorService extends AbstractValidatorService {
    public NoReturnCodeInPrintfValidatorService() {
        super(CheckerType.NO_RETURN_CODE_IN_PRINTF);
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoReturnCodeInPrintfVisitor(validator));
    }
}
