package jp.cafebabe.fritter.utils;

@FunctionalInterface
public interface ExceptionableSupplier<T, E extends Throwable> {
    T get() throws E;
}
