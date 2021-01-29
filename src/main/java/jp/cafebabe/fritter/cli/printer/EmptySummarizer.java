package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.ResultSet;

public class EmptySummarizer implements Converter<ResultSet>, Summarizer {
    @Override
    public String convert(ResultSet rs) {
        return "";
    }
}
