package jp.cafebabe.fritter.validators.impl.nest;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

class IndentLevelStream {
    private IndentLevel buildIndentLevel(String line, String trim) {
        char firstChar = trim.charAt(0);
        int indent = line.indexOf(firstChar);
        return new IndentLevel((indent == 0? 0: indent - 2) / 2);
    }

    private IndentLevel buildIndentLevel(String line) {
        return buildIndentLevel(line, line.trim());
    }

    private IndentLevel parseIndentLevel(String line) {
        Optional<String> indentLevel = optional(line);
        return indentLevel.map(this::buildIndentLevel)
                .orElse(new IndentLevel(0));
    }

    public Stream<IndentLevel> stream(IndentManipulator manipulator) {
        return manipulator.stream()
                .map(this::parseIndentLevel)
                .distinct();
    }

    private Optional<String> optional(String line) {
        if(Objects.equals(line, ""))
            return Optional.empty();
        return Optional.of(line);
    }
}
