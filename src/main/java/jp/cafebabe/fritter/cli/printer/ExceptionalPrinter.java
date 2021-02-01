package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.config.Format;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.validators.Validators;

import java.io.PrintWriter;

public class ExceptionalPrinter implements Printer {
    public static final class Service implements PrinterService {
        private Format format;

        public Service(Format format) {
            this.format = format;
        }

        @Override
        public Format format() {
            return null;
        }

        @Override
        public Printer build(ResultOptions opts) {
            return new ExceptionalPrinter(format);
        }

        @Override
        public Summarizer summarizer() {
            return null;
        }

        @Override
        public ValidatorsConverter validatorsConverter() {
            return null;
        }
    }
    private Format format;

    public ExceptionalPrinter(Format format){
        this.format = format;
    }

    @Override
    public Summarizer summarizer() {
        return null;
    }

    @Override
    public ValidatorsConverter validatorsConveter() {
        return null;
    }

    @Override
    public boolean print(PrintWriter out, Validators validators, ResultSet rs) {
        throw new UnknownFormatException("unknown format: " + format);
    }

    @Override
    public void printValidators(PrintWriter out, Validators validators) {
    }

    @Override
    public void printSummary(PrintWriter out, ResultSet rs) {
    }

    @Override
    public void printResults(PrintWriter out, ResultSet rs) {
    }
}
