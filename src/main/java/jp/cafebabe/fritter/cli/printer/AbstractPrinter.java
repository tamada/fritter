package jp.cafebabe.fritter.cli.printer;

public abstract class AbstractPrinter implements Printer {
    private Summarizer summarizer;
    private ValidatorsConverter validatorsConverter;

    public AbstractPrinter(ValidatorsConverter converter, Summarizer summarizer) {
        this.validatorsConverter = converter;
        this.summarizer = summarizer;
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
