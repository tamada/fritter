package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViolationsJsonier implements Jsonier<Violations> {
    private Jsonier<Violation> jsonier = violation -> violation.accept(new JsonViolationsVisitor());

    @Override
    public String convert(Violations item) {
        return toJsonArray(item.stream()
                .map(violation -> jsonier.convert(violation)));
    }

    private Stream<String> exceptions(Violations violations) {
        return violations.exceptions()
                .map(exception -> string(exception.getLocalizedMessage()));
    }
}
