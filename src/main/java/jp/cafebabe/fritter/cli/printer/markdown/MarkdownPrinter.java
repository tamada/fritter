package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.printer.AbstractPrinter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.entities.ResultsSet;

import java.io.PrintWriter;

public class MarkdownPrinter extends AbstractPrinter {
    public MarkdownPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        super(converter, summarizer);
    }

    @Override
    public void printHeader(PrintWriter out, ResultsSet rs) {
        out.println("# fritter results");
        out.println();
        out.printf("Date: %s%n%n", nowString());
    }

    @Override
    public void printFooter(PrintWriter out, ResultsSet rs) {
        out.flush();
    }

    @Override
    public void printResults(PrintWriter out, ResultsSet rs) {
        new ResultSetMarkdowner(out).convert(rs);
    }
}
