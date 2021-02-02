package jp.cafebabe.fritter.cli.printer.xml;

import jp.cafebabe.fritter.cli.printer.Converter;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.ValidateException;
import jp.cafebabe.fritter.validators.Violation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ViolationsVisitor implements jp.cafebabe.fritter.entities.visitors.ViolationsVisitor<String> {
    private Converter<Violation> converter = violation -> violation.accept(new ViolationVisitor());
    private StringBuilder sb = new StringBuilder();
    private List<Exception> exceptions = new ArrayList<>();

    @Override
    public void visitDataSource(DataSource source) {
        sb.append("<file><file-name>")
                .append(source.relativePath())
                .append("</file-name><violations>");
    }

    @Override
    public void visitViolation(Violation violation) {
        sb.append(converter.convert(violation));
    }

    @Override
    public void visitValidateException(ValidateException e) {
        exceptions.add(e);
    }

    @Override
    public String visitEnd() {
        sb.append("</violations>");
        sb.append(exceptions());
        sb.append("</file>");
        return new String(sb);
    }

    private String exceptions() {
        if(exceptions.isEmpty())
            return "";
        return exceptionsImpl();
    }

    private String exceptionsImpl() {
        return exceptions.stream()
                .map(e -> convert(e))
                .collect(Collectors.joining("", "<exceptions>", "</exceptions>"));
    }

    private String convert(Exception e) {
        return String.format("<exception><name>%s</name><message>%s</message></exception>",
                e.getClass().getName(),
                e.getLocalizedMessage());
    }
}
