package jp.cafebabe.fritter.entities.sources;

import java.io.IOException;
import java.nio.file.Path;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;

class CompilationUnitBuilder {
    public CompilationUnit buildUnit(Path path) throws IOException {
        ParseResult<CompilationUnit> result = new JavaParser().parse(path);
        return result.getResult()
                .orElseThrow();
    }
}
