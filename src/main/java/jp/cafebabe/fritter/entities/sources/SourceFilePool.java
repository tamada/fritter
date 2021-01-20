package jp.cafebabe.fritter.entities.sources;

import jp.cafebabe.fritter.utils.PathHelper;

import java.nio.file.Path;
import java.util.stream.Stream;

public class SourceFilePool extends AbstractSourcePool {
    public SourceFilePool(Path file) {
        super(file);
    }

    public Stream<DataSource> stream() {
        if(PathHelper.endsWith(base(), ".java"))
            return Stream.of(new DefaultDataSource(this, base()));
        return Stream.empty();
    }
}
