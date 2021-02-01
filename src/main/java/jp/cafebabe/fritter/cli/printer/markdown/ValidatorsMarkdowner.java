package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.validators.Validators;

import java.util.stream.Collectors;

public class ValidatorsMarkdowner implements ValidatorsConverter {
    @Override
    public String convert(Validators validators) {
        return String.format("## Validators%n%n%s%n%n",
                validators.names().map(type -> "* " + type)
                        .collect(Collectors.joining(System.lineSeparator())));
    }
}
