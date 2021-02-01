package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.cli.printer.*;
import jp.cafebabe.fritter.config.Format;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.validators.Validators;
import jp.cafebabe.fritter.validators.Violations;
import picocli.CommandLine.Option;

import java.util.function.Predicate;

public class ResultOptions {
    @Option(names={"-n", "--no-summary"}, description="does not show the summary of analysis.")
    private boolean noSummary = false;

    @Option(names={"-s", "--show-no-violated-files"}, description="shows file names with no violations.")
    private boolean showNoViolatedFiles = false;

    @Option(names={"-i", "--no-validator-info"}, description="does not show the validator information for analyses.")
    private boolean noValidatorInfo = false;

    @Option(names={"-f", "--format"}, paramLabel="FORMAT",
            description={"specifies the resultant format. Default is json.",
                    "Available values: json, markdown, yaml, and xml"},
            converter = FormatConverter.class)
    private Format format = Format.Json;

    public void print(Validators validators, ResultSet rs) {
        buildPrinter()
                .print(validators, new DelegatesResultSet(rs, createFilter(showNoViolatedFiles)));
    }

    private Predicate<Pair<DataSource, Violations>> createFilter(boolean showNoViolatedFiles) {
        if(showNoViolatedFiles)
            return (pair) -> true;
        return (pair) -> pair.test(
                (ds, violations) -> violations.hasViolations());
    }

    public Printer buildPrinter() {
        PrinterBuilder builder = new PrinterBuilder();
        return builder.build(format, this);
    }

    public ValidatorsConverter validatorsConverter(PrinterService service) {
        if(noValidatorInfo)
            return new EmptyValidatorsConverter();
        return service.validatorsConverter();
    }

    public Summarizer summarizer(PrinterService service){
        if(noSummary)
            return new EmptySummarizer();
        return service.summarizer();
    }
}
