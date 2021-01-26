package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.AbstractPrinter;
import jp.cafebabe.fritter.cli.printer.Joiner;
import jp.cafebabe.fritter.cli.printer.Printer;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonPrinter extends AbstractPrinter {
    public JsonPrinter(Summarizer summarizer) {
        super(summarizer);
    }

    @Override
    public Stream<Pair<DataSource, Violations>> stream(ResultSet rs, SourcePool pool) {
        return rs.stream(pool);
    }

    @Override
    public void printHeader(PrintWriter out, ResultSet rs) {
        out.print("{");
    }

    @Override
    public void printFooter(PrintWriter out, ResultSet rs) {
        out.println("}");
    }

    @Override
    public void printBody(PrintWriter out, ResultSet rs) {
        out.print("\"results\":");
        out.print(convert(rs));
    }

    @Override
    public String convert(Violation violation) {
        return violation.accept(new ToJsonier());
    }

    @Override
    public String convert(ResultSet rs) {
        return rs.pools()
                .map(pool -> convertResultSet(rs, pool))
               .collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public String convert(Violations violations) {
        return violations.stream()
                .map(violation -> convert(violation))
                .collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public String convert(DataSource source, Violations violations) {
        return String.format("{\"file\":\"%s\",\"messages\":%s}",
                source.relativePath(),
                convert(violations));
    }

    private String convertStream(Stream<Pair<DataSource, Violations>> stream) {
        return stream.map(pair -> convert(pair.reduce((l, r) -> l), pair.reduce((l, r) -> r)))
                .collect(Collectors.joining(",", "[", "]"));
    }

    private String convertResultSet(ResultSet rs, SourcePool pool) {
        return String.format("{\"base\":\"%s\",\"violations\":%s}",
                pool.base(),
                convertStream(stream(rs, pool)));
    }
}
