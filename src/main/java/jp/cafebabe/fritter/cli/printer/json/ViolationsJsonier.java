package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

public class ViolationsJsonier implements Jsonier<Violations> {
    private Jsonier<Violation> jsonier = violation -> violation.accept(new JsonViolationVisitor());

    @Override
    public String convert(Violations item) {
        return toJsonArray(item.stream()
                .map(violation -> jsonier.convert(violation)));
    }
}
