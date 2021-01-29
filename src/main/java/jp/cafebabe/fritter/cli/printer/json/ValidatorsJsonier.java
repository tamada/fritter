package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.validators.Validators;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;
import static jp.cafebabe.fritter.cli.printer.json.Jsonier.toJsonArray;

public class ValidatorsJsonier implements Jsonier<Validators>, ValidatorsConverter {
    @Override
    public String convert(Validators item) {
        return String.format("%s:%s,", string("validators"),
                toJsonArray(item.names()
                        .map(Jsonier::string)));
    }
}
