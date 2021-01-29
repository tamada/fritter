package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.printer.AbstractPrinter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.cli.printer.json.ResultSetJsonier;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.validators.Validators;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MarkdownPrinter extends AbstractPrinter {
    public MarkdownPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        super(converter, summarizer);
    }

    @Override
    public void printHeader(PrintWriter out, ResultSet rs) {
        out.println("# fritter results");
        out.println();
        out.printf("Date: %s%n%n", nowString());
    }

    @Override
    public void printFooter(PrintWriter out, ResultSet rs) {
        out.flush();
    }

    @Override
    public void printResults(PrintWriter out, ResultSet rs) {
        new ResultSetMarkdowner(out).convert(rs);
    }
}
