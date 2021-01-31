package jp.cafebabe.fritter.cli.printer.xml;

import jp.cafebabe.fritter.cli.printer.Converter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.entities.ResultsSet;

public class XmlSummarizer implements Summarizer, Converter<ResultsSet> {
    @Override
    public String convert(ResultsSet rs) {
        return String.format("<summary>%s%s%s</summary>",
                violatedFileCount(rs), targetFile(rs), violations(rs));
    }

    private String violations(ResultsSet rs) {
        return String.format("<violation-count>%s</violation-count>", violationCount(rs));
    }

    private String targetFile(ResultsSet rs) {
        return String.format("<target-file-count>%s</target-file-count>", targetCount(rs));
    }

    private String violatedFileCount(ResultsSet rs) {
        return String.format("<violated-file-count>%s</violated-file-count>", violatedTargetCount(rs));
    }
}
