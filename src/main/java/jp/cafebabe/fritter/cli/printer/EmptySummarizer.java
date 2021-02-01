package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.entities.ResultsSet;

public class EmptySummarizer implements Converter<ResultsSet>, Summarizer {
    @Override
    public String convert(ResultsSet rs) {
        return "";
    }
}
