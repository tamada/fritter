package jp.cafebabe.fritter.cli.printer.xml;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.cli.printer.Printer;
import jp.cafebabe.fritter.cli.printer.PrinterService;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.config.Format;

public class XmlPrinterService implements PrinterService {
    @Override
    public Format format() {
        return Format.Xml;
    }

    @Override
    public Printer build(ResultOptions opts) {
        return new XmlPrinter(opts.validatorsConverter(this), opts.summarizer(this));
    }

    @Override
    public ValidatorsConverter validatorsConverter() {
        return new ValidatorsXmlConverter();
    }

    @Override
    public Summarizer summarizer() {
        return new XmlSummarizer();
    }
}
