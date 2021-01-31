package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.Count;
import jp.cafebabe.fritter.entities.ResultsSet;

public interface Summarizer {
    String convert(ResultsSet rs);

    default Count violatedTargetCount(ResultsSet rs) {
        return new Count(rs.stream()
                .filter(pair -> pair.test(
                        (left, right) -> right.hasViolations())).count());
    }

    default Count targetCount(ResultsSet rs) {
        return new Count(rs.stream().count());
    }

    default Count violationCount(ResultsSet rs) {
        return new Count(rs.stream()
                .map(pair -> pair.reduce((left, right) -> right))
                .mapToLong(violations -> violations.accept(new ViolationCounter()))
                .sum());
    }

}
