package jp.cafebabe.fritter.cli.printer.markdown;

import jp.cafebabe.fritter.cli.printer.Converter;

import java.io.PrintWriter;

public abstract class Markdowner<T> implements Converter<T> {
    protected PrintWriter out;

    public Markdowner(PrintWriter out) {
        this.out = out;
    }
}
