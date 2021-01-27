package jp.cafebabe.fritter.cli.printer.json;

import jp.cafebabe.fritter.cli.printer.AbstractPrinter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.validators.Validators;

import java.io.PrintWriter;

public class JsonPrinter extends AbstractPrinter {
    public JsonPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        super(converter, summarizer);
    }

    @Override
    public void printHeader(PrintWriter out, ResultSet rs) {
        out.print("{");
    }

    @Override
    public void printFooter(PrintWriter out, ResultSet rs) {
        out.println("}");
    }

    @Override
    public void printValidators(PrintWriter out, Validators validators) {
        out.print(validatorsConveter().convert(validators));
    }

    @Override
    public void printSummary(PrintWriter out, ResultSet rs) {
        out.print(summarizer().convert(rs));
    }

    @Override
    public void printResults(PrintWriter out, ResultSet rs) {
        out.print(new ResultSetJsonier().convert(rs));
    }
}
