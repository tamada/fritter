package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.validators.Validators;

public class ValidatorsJsonier implements Jsonier<Validators>, ValidatorsConverter {
    @Override
    public String convert(Validators item) {
        return String.format("%s:%s,", string("validators"),
                toJsonArray(item.names()
                        .map(this::string)));
    }
}
