package jp.cafebabe.fritter.validators.impl;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class LineCalculator {
    private CompilationUnit unit;

    public LineCalculator(CompilationUnit unit) {
        this.unit = unit;
    }

    public int lineNumber(ASTNode node) {
        return unit.getLineNumber(node.getStartPosition());
    }

    public int lineCount(ASTNode node) {
        int startLine = unit.getLineNumber(node.getStartPosition());
        int endLine = calculateEndLine(node);
        return endLine - startLine + 1;
    }

    private int calculateEndLine(ASTNode node) {
        int start = node.getStartPosition();
        int end = start + node.getLength();
        return unit.getLineNumber(end);
    }
}
