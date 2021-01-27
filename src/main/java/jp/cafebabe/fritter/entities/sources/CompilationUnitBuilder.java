package jp.cafebabe.fritter.entities.sources;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

class CompilationUnitBuilder {
    public CompilationUnit buildUnit(Path path) throws IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS12);
        parser.setSource(source(path));
        return (CompilationUnit) parser.createAST(new NullProgressMonitor());
    }

    private char[] source(Path path) throws IOException {
        return Files.lines(path)
                .collect(Collectors.joining(System.getProperty("line.separator")))
                .toCharArray();
    }

    private char[] merge(char[] first, char[] seconds) {
        char[] array = new char[first.length + seconds.length];
        System.arraycopy(first, 0, array, 0, first.length);
        System.arraycopy(seconds, 0, array, first.length, seconds.length);
        return array;
    }
}
