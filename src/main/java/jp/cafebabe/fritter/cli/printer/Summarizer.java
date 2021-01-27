package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.Count;
import jp.cafebabe.fritter.entities.ResultSet;

import java.io.PrintWriter;

public interface Summarizer {
    String convert(ResultSet rs);

    default Count violatedTargetCount(ResultSet rs) {
        return new Count(rs.stream()
                .filter(pair -> pair.test(
                        (left, right) -> !right.isEmpty())).count());
    }

    default Count targetCount(ResultSet rs) {
        return new Count(rs.stream().count());
    }

    default Count violationCount(ResultSet rs) {
        return new Count(rs.stream()
                .map(pair -> pair.reduce((left, right) -> right))
                .mapToLong(violations -> violations.stream().count())
                .sum());
    }
}
