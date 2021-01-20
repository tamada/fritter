package jp.cafebabe.fritter.entities.sources;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface SourcePool {
    Stream<DataSource> stream();

    Path base();
}
