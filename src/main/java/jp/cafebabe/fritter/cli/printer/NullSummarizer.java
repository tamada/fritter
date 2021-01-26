package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.ResultSet;

import java.io.PrintWriter;

public class NullSummarizer implements Summarizer {
    @Override
    public String summary(ResultSet rs) {
        return "";
    }
}
