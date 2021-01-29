package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.options.DelegatesResultSet;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultSet;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;

public class SummaryJsonizer implements Jsonier<ResultSet>, Summarizer {
    @Override
    public String convert(ResultSet rs) {
        return String.format(",%s:%s",
                string("summary"), summaryString(rs));
    }

    private String summaryString(ResultSet rs) {
        return String.format("{\"violated-files\":%s,\"total-files\":%s,\"violated-file-count\":%s}",
                violatedTargetCount(rs), targetCount(rs), violationCount(rs));
    }
}
