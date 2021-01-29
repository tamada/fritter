package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Violations;

import java.util.stream.Stream;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;
import static jp.cafebabe.fritter.cli.printer.json.Jsonier.toJsonArray;

public class ResultSetJsonier implements Jsonier<ResultSet> {
    private Jsonier<Violations> jsonier = violations -> violations.accept(new ViolationsVisitor());

    public String convert(ResultSet rs) {
        return String.format("%s:%s", string("results"),
                toJsonArray(convertImpl(rs.stream())));
    }

    private Stream<String> convertImpl(Stream<Pair<DataSource, Violations>> stream) {
        return stream.map(pair -> pair.reduce((left, right) -> right))
                .map(violations -> jsonier.convert(violations));
    }
}
