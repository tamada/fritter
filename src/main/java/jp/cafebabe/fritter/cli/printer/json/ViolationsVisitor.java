package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

import java.util.ArrayList;
import java.util.List;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;
import static jp.cafebabe.fritter.cli.printer.json.Jsonier.toJsonArray;

class ViolationsVisitor implements jp.cafebabe.fritter.entities.visitors.ViolationsVisitor<String> {
    private Jsonier<Violation> jsonier = violation -> violation.accept(new ViolationVisitor());
    private List<String> exceptions = new ArrayList<>();
    private List<String> violations = new ArrayList<>();
    private String source;

    @Override
    public void visitDataSource(DataSource source) {
        this.source = source.relativePath()
                .toString();
    }

    @Override
    public void visitViolation(Violation violation) {
        violations.add(jsonier.convert(violation));
    }

    @Override
    public void visitValidateException(ValidateException e) {
        exceptions.add(String.format("\"%s\":\"%s\"",
                e.getClass().getName(),
                e.getLocalizedMessage()));
    }

    @Override
    public String visitEnd() {
        return String.format("{\"file\":%s,\"messages\":%s%s}", string(source),
                toJsonArray(violations.stream()),
                exceptionsString());
    }

    private String exceptionsString() {
        if(exceptions.isEmpty())
            return "";
        return String.format(",\"exceptions\":%s",
                toJsonArray(exceptions.stream()));
    }
}
