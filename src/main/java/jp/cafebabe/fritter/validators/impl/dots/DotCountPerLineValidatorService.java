package jp.cafebabe.fritter.validators.impl.dots;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class DotCountPerLineValidatorService extends AbstractValidatorService {
    public DotCountPerLineValidatorService() {
        super(CheckerType.DOT_COUNT_PER_LINE);
    }

    @Override
    public Validator build(Parameter param) {
        return new DotCountPerLineValidator(this, param);
    }
}
