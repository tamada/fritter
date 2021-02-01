package jp.cafebabe.fritter.cli.printer;

import jp.cafebabe.fritter.cli.options.DelegatesResultSet;
import jp.cafebabe.fritter.entities.ResultSet;
import jp.cafebabe.fritter.validators.Validators;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractPrinter implements Printer {
    private Summarizer summarizer;
    private ValidatorsConverter validatorsConverter;

    public AbstractPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        this.validatorsConverter = converter;
        this.summarizer = summarizer;
    }

    public String nowString() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public final void printValidators(PrintWriter out, Validators validators) {
        out.print(validatorsConveter().convert(validators));
    }

    @Override
    public final void printSummary(PrintWriter out, ResultSet rs) {
        out.print(summarizer().convert(stripResultSet(rs)));
    }

    private ResultSet stripResultSet(ResultSet rs) {
        if(rs instanceof DelegatesResultSet)
            return ((DelegatesResultSet)rs).delegated();
        return rs;
    }

    @Override
    public final ValidatorsConverter validatorsConveter() {
        return validatorsConverter;
    }

    @Override
    public final Summarizer summarizer(){
        return summarizer;
    }
}
