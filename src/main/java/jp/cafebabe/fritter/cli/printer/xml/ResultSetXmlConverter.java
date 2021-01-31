package jp.cafebabe.fritter.cli.printer.xml;

import jp.cafebabe.fritter.cli.printer.Converter;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultsSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultSetXmlConverter implements Converter<ResultsSet> {
    private Converter<Violations> converter = violations -> violations.accept(new XmlViolationsVisitor());

    @Override
    public String convert(ResultsSet item) {
        return String.format("<results>%s</results>", convertImpl(item));
    }

    private String convertImpl(ResultsSet rs) {
        return rs.pools()
                .map(pool -> convert(rs, pool))
                .collect(Collectors.joining());
    }

    private String convert(ResultsSet rs, SourcePool pool) {
        return String.format("<result><base-directory>%s</base-directory><files>%s</files></result>",
                pool.base(), violations(rs.stream(pool)));
    }

    private String violations(Stream<Pair<DataSource, Violations>> stream) {
        return stream.map(pair -> pair.reduce((left, right) -> right))
                .map(violations -> converter.convert(violations))
                .collect(Collectors.joining());
    }
}
