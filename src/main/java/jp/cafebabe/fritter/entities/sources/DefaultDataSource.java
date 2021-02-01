package jp.cafebabe.fritter.entities.sources;

import com.github.javaparser.ast.CompilationUnit;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Pair;
import jp.cafebabe.fritter.utils.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    public Stream<Pair<Location.LineNumber, String>> lines() throws IOException {
        return Streams.zip(lineNumbers(),
                Files.lines(path()));
    }

    private Stream<Location.LineNumber> lineNumbers() {
        return IntStream.iterate(1, i -> i + 1)
                .mapToObj(number -> Location.of(number));
    }

    private CompilationUnit buildUnit(Path path) throws IOException {
        return new CompilationUnitBuilder()
                .buildUnit(path);
    }
}
