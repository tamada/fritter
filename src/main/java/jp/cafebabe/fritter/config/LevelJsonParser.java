package jp.cafebabe.fritter.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jp.cafebabe.fritter.entities.Name;
import jp.cafebabe.fritter.entities.Pair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LevelJsonParser {
    public Level toLevel(JsonObject object) {
        Name name = Name.of(object.get("name").getAsString());
        List<Pair<CheckerType, Parameter>> list = createParameters(object);
        return new Level(name, list.stream());
    }

    private List<Pair<CheckerType, Parameter>> createParameters(JsonObject object) {
        List<String> names = findCheckerTypes(object.getAsJsonArray("validators"));
        return findParameters(names, object.getAsJsonObject("parameters"));
    }

    private List<String> findCheckerTypes(JsonArray array) {
        return IntStream.range(0, array.size())
                .mapToObj(index -> array.get(index).getAsString())
                .collect(Collectors.toList());
    }

    private List<Pair<CheckerType, Parameter>> findParameters(List<String> names, JsonObject object) {
        return names.stream()
                .map(name -> toPair(name, object))
                .collect(Collectors.toList());
    }

    private Pair<CheckerType, Parameter> toPair(String name, JsonObject object) {
        Optional<Parameter> value = Optional.ofNullable(object.get(name))
                .map(element -> new Parameter(element.getAsInt()));
        return Pair.of(CheckerType.of(name), value.orElse(Parameter.EMPTY));
    }
}
