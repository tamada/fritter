package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.options.DelegatesResultSet;
import jp.cafebabe.fritter.cli.printer.Converter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultSet;

public class SummaryJsonizer implements Jsonier<ResultSet>, Summarizer {
    @Override
    public String convert(ResultSet rs) {
        return String.format(",%s:%s",
                string("summary"), summaryString(rs));
    }

    private String summaryString(ResultSet rs) {
        if(rs instanceof DelegatesResultSet)
            rs = ((DelegatesResultSet)rs).delegated();
        return summaryStringImpl(rs);
    }

    private String summaryStringImpl(ResultSet rs) {
        return String.format("{\"violated-files\":%s,\"total-files\":%s,\"violated-file-count\":%s}",
                violatedTargetCount(rs), targetCount(rs), violationCount(rs));
    }
}
