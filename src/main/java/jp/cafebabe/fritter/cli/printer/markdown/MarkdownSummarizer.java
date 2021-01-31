package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultsSet;

public class MarkdownSummarizer implements Summarizer {
    @Override
    public String convert(ResultsSet rs) {
        return String.format("%n## Summary%n%n%s%n", summaryString(rs));
    }

    private String summaryString(ResultsSet rs) {
        return String.format("* violated files: %s%n* total files: %s%n* violated file count: %s",
                violatedTargetCount(rs), targetCount(rs), violationCount(rs));
    }
}
