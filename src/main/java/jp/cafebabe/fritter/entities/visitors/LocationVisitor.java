package jp.cafebabe.fritter.entities.visitors;

public interface LocationVisitor {
    void visitLocation(int lineNumber);

    void visitLocation(int[] lineNumbers);

    void visitLocation(String packageName);
}
