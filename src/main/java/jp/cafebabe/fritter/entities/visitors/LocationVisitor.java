package jp.cafebabe.fritter.entities.visitors;

public interface LocationVisitor<S> {
    S visitLocation(int lineNumber);

    S visitLocation(int[] lineNumbers);

    S visitLocation(String packageName);
}
