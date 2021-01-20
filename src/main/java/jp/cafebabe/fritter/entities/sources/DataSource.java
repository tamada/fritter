package jp.cafebabe.fritter.entities.sources;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import io.vavr.control.Try;
import jp.cafebabe.fritter.entities.Name;
import org.eclipse.jdt.core.dom.CompilationUnit;

public interface DataSource {
    SourcePool sourcePool();
    Path path();
    CompilationUnit ast() throws IOException;
    Reader sourceFile() throws IOException;

    default Path base() {
        return sourcePool().base();
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
