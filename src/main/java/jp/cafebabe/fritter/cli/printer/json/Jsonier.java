package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.Converter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface Jsonier<T> extends Converter<T> {
    default String string(String item) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(item).append("\"");
        return new String(sb);
    }

    default String string(Object object) {
        return string(object.toString());
    }

    default String toJsonArray(Stream<String> stream) {
        return stream
                .collect(Collectors.joining(",", "[", "]"));
    }
}
