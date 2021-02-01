package jp.cafebabe.fritter.cli.printer.xml;

import jp.cafebabe.fritter.cli.printer.AbstractPrinter;
import jp.cafebabe.fritter.cli.printer.Summarizer;
import jp.cafebabe.fritter.cli.printer.ValidatorsConverter;
import jp.cafebabe.fritter.entities.ResultsSet;

import java.io.PrintWriter;

public class XmlPrinter extends AbstractPrinter {
    public XmlPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        super(converter, summarizer);
    }

    @Override
    public void printHeader(PrintWriter out, ResultsSet rs) {
        out.println("<?xml version=\"1.0\"?>");
        out.print("<fritter>");
    }

    @Override
    public void printFooter(PrintWriter out, ResultsSet rs) {
        out.println("</fritter>");
        out.flush();
    }

    @Override
    public void printResults(PrintWriter out, ResultsSet rs) {
        out.print(new ResultSetXmlConverter()
                .convert(rs));
    }
}
