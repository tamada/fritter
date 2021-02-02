package jp.cafebabe.fritter.validators.impl;

import com.github.javaparser.Position;
import com.github.javaparser.ast.Node;

import java.util.Optional;

public class LineCalculatorUtils {
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
