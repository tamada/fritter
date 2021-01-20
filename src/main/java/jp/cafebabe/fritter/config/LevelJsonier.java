package jp.cafebabe.fritter.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jp.cafebabe.fritter.entities.Name;

import java.util.Map;
import java.util.Set;

class LevelJsonier {
    public String toJson(Name name, Map<CheckerType, Parameter> params) {
        Gson gson = new Gson();
        return gson.toJson(toJsonObject(name, params));
    }

    public JsonObject toJsonObject(Name name, Map<CheckerType, Parameter> params) {
        JsonObject object = new JsonObject();
        putEntries(object, name, params);
        return object;
    }

    private void putEntries(JsonObject object, Name name, Map<CheckerType, Parameter> params) {
        object.addProperty("name", name.toString());
        putCheckerEntries(object, params);
        object.add("parameters", createParamJSON(params));
    }

    private JsonArray toJsonArray(Set<CheckerType> set) {
        JsonArray array = new JsonArray();
        set.stream().map(CheckerType::toString)
                .forEach(array::add);
        return array;
    }

    private void putCheckerEntries(JsonObject object, Map<CheckerType, Parameter> params) {
        JsonArray array = toJsonArray(params.keySet());
        object.add("checkers", array);
    }

    private JsonObject createParamJSON(Map<CheckerType, Parameter> params) {
        JsonObject paramJson = new JsonObject();
        params.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .forEach(entry -> putEach(paramJson, entry));
        return paramJson;
    }

    private void putEach(JsonObject params, Map.Entry<CheckerType, Parameter> entry) {
        params.addProperty(entry.getKey().toString(),
                entry.getValue().value());
    }
}
