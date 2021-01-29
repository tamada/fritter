package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.cli.printer.Printer;
import jp.cafebabe.fritter.cli.printer.PrinterService;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.config.Format;

public class JsonPrinterService implements PrinterService {
    @Override
    public Format format() {
        return Format.Json;
    }

    @Override
    public Printer build(ResultOptions opts) {
        return new JsonPrinter(opts.validatorsConverter(this), opts.summarizer(this));
    }

    @Override
    public ValidatorsConverter validatorsConverter() {
        return new ValidatorsJsonier();
    }

    @Override
    public Summarizer summarizer() {
        return new SummaryJsonizer();
    }
}
