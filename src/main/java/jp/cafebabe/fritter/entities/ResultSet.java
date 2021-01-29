package jp.cafebabe.fritter.entities;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;
import jp.cafebabe.fritter.validators.ViolationsMerger;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ResultSet {
    private Map<SourcePool, Map<DataSource, Violations>> map = new HashMap<>();

    public ResultSet() {
    }

    public Stream<Pair<DataSource, Violations>> stream() {
        return pools().flatMap(this::stream);
    }

    public Stream<SourcePool> pools() {
        return map.keySet()
                .stream();
    }

    public Stream<Pair<DataSource, Violations>> stream(SourcePool pool) {
        return map.getOrDefault(pool, new HashMap<>())
                .entrySet().stream()
                .map(entry -> Pair.of(entry));
    }

    public void put(Violations violations) {
        DataSource source = violations.source();
        putImpl(violations, source, source.sourcePool());
    }

    private void putImpl(Violations violations, DataSource source, SourcePool pool) {
        Map<DataSource, Violations> value = map.getOrDefault(pool, new HashMap<>());
        updateValue(violations, source, value);
        map.put(pool, value);
    }

    private void updateValue(Violations v1, DataSource source, Map<DataSource, Violations> value) {
        Violations v2 = value.getOrDefault(source, new Violations(source));
        value.put(source,
                new ViolationsMerger().merge(v1, v2));
    }
}
