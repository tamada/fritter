package jp.cafebabe.fritter.entities.sources;

import com.github.javaparser.ast.CompilationUnit;
import io.vavr.control.Try;
import jp.cafebabe.fritter.entities.Location;
import jp.cafebabe.fritter.entities.Name;
import jp.cafebabe.fritter.entities.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface DataSource {
    SourcePool sourcePool();
    Path path();
    CompilationUnit ast() throws IOException;
    Stream<Pair<Location.LineNumber, String>> lines() throws IOException;

    default Path base() {
        return sourcePool().base();
    }

    default Path relativePath() {
        return base().relativize(path());
    }

    default Name name() {
        return Name.of(base().relativize(path())
                .toString());
    }

    default boolean isSame(DataSource other) {
        return Try.of(() -> Files.isSameFile(path(), other.path()))
                .getOrElse(false);
    }
}
