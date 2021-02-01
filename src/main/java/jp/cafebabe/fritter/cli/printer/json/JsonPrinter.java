package jp.cafebabe.fritter.cli.printer.json;

import static jp.cafebabe.fritter.cli.printer.json.Jsonier.string;

import jp.cafebabe.fritter.cli.printer.AbstractPrinter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.entities.ResultSet;

import java.io.PrintWriter;

public class JsonPrinter extends AbstractPrinter {
    public JsonPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        super(converter, summarizer);
    }

    @Override
    public void printHeader(PrintWriter out, ResultSet rs) {
        out.printf("{%s:%s,", string("date"),
                string(nowString()));
    }

    @Override
    public void printFooter(PrintWriter out, ResultSet rs) {
        out.println("}");
    }

    @Override
    public void printResults(PrintWriter out, ResultSet rs) {
        out.print(new ResultSetJsonier().convert(rs));
    }
}
