package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.options.DelegatesResultSet;
import jp.cafebabe.fritter.cli.printer.Converter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultSet;

public class MarkdownSummarizer implements Summarizer {
    @Override
    public String convert(ResultSet rs) {
        return String.format("%n## Summary%n%n%s%n", summaryString(rs));
    }

    private String summaryString(ResultSet rs) {
        return String.format("* violated files: %s%n* total files: %s%n* violated file count: %s",
                violatedTargetCount(rs), targetCount(rs), violationCount(rs));
    }
}
