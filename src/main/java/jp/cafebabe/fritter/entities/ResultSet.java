package jp.cafebabe.fritter.entities;

import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violations;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ResultSet {
    private Map<SourcePool, Violations> map = new HashMap<>();

    public ResultSet() {
    }

    public void put(Violations violations) {
        putImpl(violations, violations.source());
    }

    private void putImpl(Violations violations, DataSource source) {
        Violations value = map.getOrDefault(source.sourcePool(), new Violations(source));
        value.merge(violations);
        map.put(source.sourcePool(), value);
    }

    public Stream<Pair<SourcePool, Violations>> stream() {
        return map.entrySet().stream()
                .map(entry -> toPair(entry));
    }

    private Pair<SourcePool, Violations> toPair(Map.Entry<SourcePool, Violations> entry) {
        return Pair.of(entry.getKey(), entry.getValue());
    }
}
