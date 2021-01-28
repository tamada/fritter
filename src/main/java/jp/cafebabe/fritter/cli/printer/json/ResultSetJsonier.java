package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.config.Parameter;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultSetJsonier implements Jsonier<ResultSet> {
    private DataSourceJsonier jsonier = new DataSourceJsonier();

    public String convert(ResultSet rs) {
        return String.format("%s:%s", string("results"),
                toJsonArray(rs.pools()
                        .map(pool -> convertResultSet(rs, pool))));
    }

    private String convertStream(Stream<Pair<DataSource, Violations>> stream) {
        return toJsonArray(stream
                .map(pair -> jsonier.convert(pair)));
    }

    private String convertResultSet(ResultSet rs, SourcePool pool) {
        return String.format("{%s:%s,%s:%s}",
                string("base"), string(pool.base()),
                string("violations"), convertStream(rs.stream(pool)));
    }

    private String exceptions(Stream<Pair<DataSource, Violations>> stream) {
        return toJsonArray(stream
                .map(pair -> jsonier.convert(pair)));
    }
}
