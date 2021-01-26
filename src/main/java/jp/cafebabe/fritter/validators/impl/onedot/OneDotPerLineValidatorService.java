package jp.cafebabe.fritter.validators.impl.onedot;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;
import jp.cafebabe.fritter.validators.spi.AbstractValidatorService;

public class OneDotPerLineValidatorService extends AbstractValidatorService {
    public OneDotPerLineValidatorService() {
        super("dot_count_per_line");
    }

    @Override
    public Validator build(Parameter param) {
        return new OneDotPerLineValidator(this, param);
    }
}
