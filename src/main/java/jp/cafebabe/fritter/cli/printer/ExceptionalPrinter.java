package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.cli.options.ResultOptions;
import jp.cafebabe.fritter.config.Format;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.entities.sources.DataSource;
import jp.cafebabe.fritter.entities.sources.SourcePool;
import jp.cafebabe.fritter.validators.Violation;
import jp.cafebabe.fritter.validators.Violations;

import java.io.PrintWriter;
import java.util.stream.Stream;

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
    public Stream<Pair<DataSource, Violations>> stream(ResultSet rs, SourcePool pool) {
        return rs.stream(pool);
    }

    @Override
    public boolean print(PrintWriter out, ResultSet rs) {
        throw new UnknownFormatException("unknown format: " + format);
    }

    @Override
    public String convert(ResultSet rs) {
        return null;
    }

    @Override
    public String convert(DataSource source, Violations violations) {
        return null;
    }

    @Override
    public String convert(Violations violations) {
        return null;
    }

    @Override
    public String convert(Violation violation) {
        return null;
    }
}
