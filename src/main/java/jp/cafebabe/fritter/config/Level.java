package jp.cafebabe.fritter.config;

import jp.cafebabe.fritter.entities.Name;
import jp.cafebabe.fritter.entities.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Level {
    private Name name;
    private Map<CheckerType, Parameter> params = new HashMap<>();

    public Level(Name name, Stream<Pair<CheckerType, Parameter>> stream) {
        this.name = name;
        stream.forEach(pair ->
                pair.accept((left, right) -> params.put(left, right)));
    }

    public boolean available(CheckerType type) {
        return params.containsKey(type);
    }

    public Parameter parameter(CheckerType type) {
        return params.get(type);
    }

    public Name name() {
        return name;
    }

    public String toJson() {
        return new LevelJsonier().toJson(name, params);
    }
}
