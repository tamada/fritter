package jp.cafebabe.fritter.cli.printer;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Joiner {
    private String delimiter = ",";
    private String prefix = "";
    private String suffix = "";

    public Joiner(String delimiter) {
        this(delimiter, "", "");
    }

    public Joiner(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String joining(Stream<String> stream) {
        return stream
                .collect(joiner());
    }

    public <I> Collector<CharSequence, ?, String> joiner() {
        return Collectors.joining(delimiter, prefix, suffix);
    }
}
