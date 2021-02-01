package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.config.Format;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class PrinterBuilder {
    private Map<Format, PrinterService> services;

    public PrinterBuilder() {
        this.services = ServiceLoader.load(PrinterService.class)
                .stream()
                .map(provider -> provider.get())
                .collect(Collectors.toMap(s -> s.format(), s -> s, (a, b) -> a));
    }

    public Printer build(Format format, ResultOptions opts) {
        PrinterService service = services.getOrDefault(format, new ExceptionalPrinter.Service(format));
        return service.build(opts);
    }
}
