package jp.cafebabe.fritter.cli.printer;

public abstract class AbstractPrinter implements Printer {
    private Summarizer summarizer;

    public AbstractPrinter(Summarizer summarizer) {
        this.summarizer = summarizer;
    }

    @Override
    public final Summarizer summarizer(){
        return summarizer;
    }
}
