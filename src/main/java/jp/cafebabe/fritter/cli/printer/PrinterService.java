package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.config.Format;

import java.util.function.Supplier;

public interface PrinterService {
    Format format();

    Printer build(ResultOptions opts);

    Summarizer summarizer();
}
