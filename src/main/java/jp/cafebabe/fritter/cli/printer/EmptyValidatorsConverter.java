package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.validators.Validators;

public class EmptyValidatorsConverter implements ValidatorsConverter{
    @Override
    public String convert(Validators validators) {
        return "";
    }
}
