package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.cli.printer.Printer;
import jp.cafebabe.fritter.cli.printer.PrinterService;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.cli.printer.json.SummaryJsonizer;
import jp.cafebabe.fritter.config.Format;

public class MarkdownPrinterService implements PrinterService {
    @Override
    public Format format() {
        return Format.Markdown;
    }

    @Override
    public Printer build(ResultOptions opts) {
        return new MarkdownPrinter(opts.validatorsConverter(this), opts.summarizer(this));
    }

    @Override
    public ValidatorsConverter validatorsConverter() {
        return new ValidatorsMarkdowner();
    }

    @Override
    public Summarizer summarizer() {
        return new MarkdownSummarizer();
    }
}
