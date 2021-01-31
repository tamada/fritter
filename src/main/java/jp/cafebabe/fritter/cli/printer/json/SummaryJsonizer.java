package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultsSet;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;

public class SummaryJsonizer implements Jsonier<ResultsSet>, Summarizer {
    @Override
    public String convert(ResultsSet rs) {
        return String.format(",%s:%s",
                string("summary"), summaryString(rs));
    }

    private String summaryString(ResultsSet rs) {
        return String.format("{\"violated-files\":%s,\"total-files\":%s,\"violated-file-count\":%s}",
                violatedTargetCount(rs), targetCount(rs), violationCount(rs));
    }
}
