package jp.cafebabe.fritter.entities.sources;

import java.nio.file.Path;
import java.util.stream.Stream;

public class EmptySourcePool extends AbstractSourcePool{
    public EmptySourcePool(Path base) {
        super(base);
    }

    @Override
    public Stream<DataSource> stream() {
        return Stream.empty();
    }
}
