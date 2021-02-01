package jp.cafebabe.fritter.validators.impl.dots;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class DotCountPerLineValidatorService extends AbstractValidatorService {
    public DotCountPerLineValidatorService() {
        super("dot_count_per_line");
    }

    @Override
    public Validator build(Parameter param) {
        return new DotCountPerLineValidator(this, param);
    }
}
