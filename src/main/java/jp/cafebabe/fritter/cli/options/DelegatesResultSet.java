package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class DelegatesResultSet extends ResultSet {
    private ResultSet rs;
    private Predicate<Pair<DataSource, Violations>> filter;

    public DelegatesResultSet(ResultSet rs) {
        this(rs, (pair) -> true);
    }

    public DelegatesResultSet(ResultSet rs, Predicate<Pair<DataSource, Violations>> filter) {
        this.rs = rs;
        this.filter = filter;
    }

    public ResultSet delegated() {
        return rs;
    }

    public void put(Violations violations) {
        rs.put(violations);
    }

    public Stream<SourcePool> pools() {
        return rs.pools();
    }

    public Stream<Pair<DataSource, Violations>> stream() {
        return rs.stream()
                .filter(filter);
    }

    public Stream<Pair<DataSource, Violations>> stream(SourcePool pool) {
        return rs.stream(pool)
                .filter(filter);
    }
}