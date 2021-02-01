package jp.cafebabe.fritter.cli.printer.json;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;
import static jp.cafebabe.fritter.cli.printer.json.Jsonier.toJsonArray;

import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultsSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.util.stream.Stream;

public class ResultSetJsonier implements Jsonier<ResultsSet> {
    private Jsonier<Violations> jsonier = violations -> violations.accept(new ViolationsVisitor());

    public String convert(ResultsSet rs) {
        return String.format("%s:%s", string("results"),
                toJsonArray(rs.pools()
                        .map(pool -> convertResultSet(rs, pool))));
    }
    private String convertResultSet(ResultsSet rs, SourcePool pool) {
        return String.format("{%s:%s,%s:%s}",
                string("base"), string(pool.base()),
                string("violations"), convertStream(rs.stream(pool)));
    }

    private String convertStream(Stream<Pair<DataSource, Violations>> stream) {
        return toJsonArray(stream
                .map(pair -> pair.reduce((left, right) -> right))
                .map(violations -> jsonier.convert(violations)));
    }
}
