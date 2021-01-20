package jp.cafebabe.fritter.entities.sources;

import jp.cafebabe.fritter.utils.DirectoryTraverser;
import jp.cafebabe.fritter.utils.PathHelper;

import java.nio.file.Path;
import java.util.stream.Stream;

public class DirectoryPool extends AbstractSourcePool {
    public DirectoryPool(Path base){
        super(base);
    }

    public Stream<DataSource> stream() {
        DirectoryTraverser dt = new DirectoryTraverser(path -> PathHelper.endsWith(path, ".java"));
        return streamImpl(dt);
    }

    private Stream<DataSource> streamImpl(DirectoryTraverser traverser) {
        return traverser.traverse(base())
                .map(path -> new DefaultDataSource(this, path));
    }
}
