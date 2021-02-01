package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.validators.Validators;

public interface ValidatorsConverter extends Converter<Validators> {
    @Override
    String convert(Validators validators);
}
