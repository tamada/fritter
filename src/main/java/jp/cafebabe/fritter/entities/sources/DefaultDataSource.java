package jp.cafebabe.fritter.entities.sources;

import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultDataSource implements DataSource {
    private SourcePool base;
    private Path path;
    private CompilationUnit unit;

    public DefaultDataSource(SourcePool base, Path path) {
        this.base = base;
        this.path = path;
    }

    @Override
    public SourcePool sourcePool() {
        return base;
    }

    @Override
    public Path path() {
        return path;
    }

    @Override
    public CompilationUnit ast() throws IOException {
        if(unit == null)
            unit = buildUnit(path);
        return unit;
    }

    @Override
    public Reader sourceFile() throws IOException {
        return Files.newBufferedReader(path());
    }

    private CompilationUnit buildUnit(Path path) throws IOException {
        return new CompilationUnitBuilder()
                .buildUnit(path);
    }
}
