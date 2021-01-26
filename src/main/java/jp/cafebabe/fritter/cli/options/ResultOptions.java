package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.cli.printer.*;
import jp.cafebabe.fritter.config.Format;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Violations;
import picocli.CommandLine.Option;

import java.util.function.Predicate;

public class ResultOptions {
    @Option(names={"-n", "--no-summary"}, description="does not show the summary of analysis.")
    private boolean noSummary = false;

    @Option(names={"-s", "--show-no-violated-files"}, description="shows file names with no violations.")
    private boolean showNoViolatedFiles = false;

    @Option(names={"-f", "--format"}, paramLabel="FORMAT",
            description={"specifies the resultant format. Default is json.",
                    "Available values: json, markdown, yaml, and xml"},
            converter = FormatConverter.class)
    private Format format = Format.Json;

    public void print(ResultSet rs) {
        buildPrinter()
                .print(new DelegatesResultSet(rs, createFilter(showNoViolatedFiles)));
    }

    private Predicate<Pair<DataSource, Violations>> createFilter(boolean showNoViolatedFiles) {
        if(showNoViolatedFiles)
            return (pair) -> true;
        return (pair) -> pair.test(
                (ds, violations) -> !violations.isEmpty());
    }

    public Printer buildPrinter() {
        PrinterBuilder builder = new PrinterBuilder();
        return builder.build(format, this);
    }

    public Summarizer summarizer(PrinterService service){
        if(noSummary)
            return new NullSummarizer();
        return service.summarizer();
    }
}
