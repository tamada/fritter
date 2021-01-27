package jp.cafebabe.fritter.cli.printer;

public interface Converter<T> {
    String convert(T item);
}
