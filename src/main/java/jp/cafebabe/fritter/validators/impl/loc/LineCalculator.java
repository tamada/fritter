package jp.cafebabe.fritter.validators.impl.loc;

import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Pair;

import java.util.stream.Stream;

public class LineCalculator {
    public LinesOfCode count(Stream<Pair<Location.LineNumber, String>> pair) {
        return LinesOfCode.of(pair.count());
    }

    public LinesOfCode count(Stream<Pair<Location.LineNumber, String>> pairStream, boolean ignoreComment) {
        return LinesOfCode.of(pairStream
                .filter(pair -> isComment(pair, ignoreComment))
                .count());
    }

    private boolean isComment(Pair<Location.LineNumber, String> pair, boolean ignoreComment) {
        return ignoreComment
                && pair.test((line, string) -> isCommentLine(string));
    }

    private boolean isCommentLine(String line) {
        return line.trim()
                .startsWith("//");
    }
}
