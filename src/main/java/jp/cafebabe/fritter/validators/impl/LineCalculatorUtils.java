package jp.cafebabe.fritter.validators.impl;

import com.github.javaparser.Position;
import com.github.javaparser.ast.Node;
import jp.cafebabe.fritter.entities.Location;

import java.util.Optional;
import java.util.stream.Stream;

public class LineCalculatorUtils {
    public static Location location(Node node) {
        return Location.of(lineNumber(node));
    }

    public static Location locations(Stream<? extends Node> nodes) {
        return Location.of(nodes.mapToInt(LineCalculatorUtils::lineNumber)
                .toArray());
    }

    public static int lineNumber(Node node) {
        Optional<Position> position = node.getBegin();
        return findLineNumber(position);
    }

    private static int findLineNumber(Optional<Position> position) {
        return position.stream()
                .mapToInt(p -> p.line)
                .findFirst().orElseGet(() -> -1);
    }

    public static int lineCount(Node node) {
        int startLine = findLineNumber(node.getBegin());
        int endLine = findLineNumber(node.getEnd());
        return endLine - startLine + 1;
    }
}
