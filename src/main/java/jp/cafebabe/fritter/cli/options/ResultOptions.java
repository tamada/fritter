package jp.cafebabe.fritter.cli.options;

import jp.cafebabe.fritter.config.Format;
import jp.cafebabe.fritter.entities.ResultSet;
import picocli.CommandLine.Option;

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
        System.out.println("print result set");
    }
}
