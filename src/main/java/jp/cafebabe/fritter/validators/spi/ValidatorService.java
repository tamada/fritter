package jp.cafebabe.fritter.validators.spi;

import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.validators.Validator;

public interface ValidatorService {
    CheckerType name();

    Validator build(Parameter param);
}
