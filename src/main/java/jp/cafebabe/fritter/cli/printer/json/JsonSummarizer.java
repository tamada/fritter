package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.DelegatesResultSet;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultSet;

import java.io.PrintWriter;

public class JsonSummarizer implements Summarizer {
    @Override
    public String summary(ResultSet rs) {
        return String.format(",\"summary\":%s", summaryString(rs));
    }

    private String summaryString(ResultSet rs) {
        if(rs instanceof DelegatesResultSet)
            rs = ((DelegatesResultSet)rs).delegated();
        return summaryStringImpl(rs);
    }

    private String summaryStringImpl(ResultSet rs) {
        return String.format("{\"violated files\":%s,\"total files\":%s,\"violated count\":%s}",
                violatedTargetCount(rs), targetCount(rs), violationCount(rs));
    }
}
