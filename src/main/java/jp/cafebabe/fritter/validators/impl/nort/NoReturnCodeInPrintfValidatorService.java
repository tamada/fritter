package jp.cafebabe.fritter.validators.impl.nort;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.VisitorAnalysisValidator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class NoReturnCodeInPrintfValidatorService extends AbstractValidatorService {
    public NoReturnCodeInPrintfValidatorService() {
        super("no_return_code_in_printf");
    }

    @Override
    public Validator build(Parameter param) {
        return new VisitorAnalysisValidator(this, param,
                (validator) -> new NoReturnCodeInPrintfVisitor(validator));
    }
}
