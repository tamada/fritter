package jp.cafebabe.fritter.cli.printer.xml;

import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.config.CheckerType;
import jp.cafebabe.fritter.validators.Validators;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidatorsXmlConverter implements ValidatorsConverter {
    @Override
    public String convert(Validators validators) {
        return String.format("<validators>%s</validators>", validators(validators.names()));
    }

    private String validators(Stream<CheckerType> stream) {
        return stream.map(type -> String.format("<validator>%s</validator>", type))
                .collect(Collectors.joining());
    }
}
